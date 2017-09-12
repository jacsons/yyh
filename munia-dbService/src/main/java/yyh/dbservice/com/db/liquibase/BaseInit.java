package yyh.dbservice.com.db.liquibase;

import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import liquibase.util.NetUtil;
import yyh.dbservice.com.db.model.EvaCache;
import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;

import javax.sql.DataSource;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.*;

/**
 * 数据库初始化记录日志函数
 * Created by oak on 2017/9/4.
 */
public class BaseInit extends SpringLiquibase
{

    /**
     * 提供给其他地方用的锁
     */
    public static String lock;

    static
    {

        try
        {
            lock = String.format("%s%s", NetUtil.getLocalHostAddress(),NetUtil.getLocalHostName());
        }
        catch (UnknownHostException e)
        {
            LoggerManager.record(LoggerType.ERROR,"BaseInit lock init UnknownHostException error");
        }
        catch (SocketException e)
        {
            LoggerManager.record(LoggerType.ERROR,"BaseInit lock init SocketException error");
        }

    }


    private static final Long TIME = System.currentTimeMillis();

    private static final  String CREATE_LOGS_TABLE = "create table databaseupdatelogs " +
            "('id' bigint(20) not null auto_increment comment 'id'," +
            "'path' varchar(1024) default '' comment 'path of changeset,'"+
            "'time' bigint (20) default 0 comment 'time when update'," +
            "primary key('id'))" +
            "engine = InnoDB default charset= utf8";

    private static final String QUERY_LOGS_TABLE = "show tabls like 'databaseupdatelogs'";

    private static final String DELETE_LOGS = "delete from 'databaseupdatelogs' where path in %s";

    private static final String INSERT_LOGS = "insert into 'databaseupdatelogs'(path,'time') value %s";

    private static final String QUERY_UPDATA_LOGS = "Select count(*) from 'databaseupdatelogs' where path in %s and 'time'  > %s";

    /**
     * 更新数据库
     * @param projectId
     * @param changeLogs
     */
    public void updata(Long projectId, String...changeLogs)
    {
        EvaCache.setProjectId(projectId.toString());
        for(String changeLog : changeLogs)
        {

            super.setChangeLog(changeLog);
            try
            {
                super.afterPropertiesSet();
            } catch (LiquibaseException e)
            {
                LoggerManager.record(LoggerType.ERROR,"can not update database!");
            }
        }
        updataTime(changeLogs);
    }

    /**
     * 检测数据库更新是否超期
      * @param projectId
     * @param changeLogs
     * @return
     */
    public boolean isOutTime(String projectId, String...changeLogs)
    {
        EvaCache.setProjectId(projectId);

        Connection connection = getConnection();
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("(");
        for(int i = 0; i < changeLogs.length; i++)
        {
            if(i == 0)
            {
                stringBuilder.append("?");
            }
            else
            {
                stringBuilder.append(",?");
            }
        }
        stringBuilder.append(")");

        try
        {

            String sql = String.format(QUERY_UPDATA_LOGS,stringBuilder.toString(), TIME);
            PreparedStatement statement = connection.prepareStatement(sql);

            for(int i = 0; i < changeLogs.length; i++)
            {
                statement.setString(i, changeLogs[i]);
            }
            //返回的应该是执行的条数。
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                int result = resultSet.getInt(1);
                if (result > 0)
                {
                    resultSet.close();
                    statement.close();
                    return true;
                }
            }
            resultSet.close();
            statement.close();
            return false;
        }
        catch (SQLException e)
        {
            LoggerManager.record(LoggerType.ERROR,"BaseInit delete sql error");
        }



        return false;
    }

    /**
     * 更新初始化的时间
     * @param changeLogs
     */
    private void updataTime(String...changeLogs)
    {
        Connection connection = getConnection();

        if(connection != null && isTableExsit(connection))
        {

            deleteLog(connection, changeLogs);
            insertLogs(connection, changeLogs);
        }

        closeConnection(connection);
    }

    /**
     * 获取connection
     * @return
     */
    private Connection getConnection()
    {
        DataSource dataSource = super.dataSource;
        Connection connection = null;

        try
        {
            connection = dataSource.getConnection();
        } catch (SQLException e)
        {
            LoggerManager.record(LoggerType.ERROR,"can not get connection");
        }
        return connection;
    }


    /**
     * 检测liquibase数据库是否存在
     * @return
     */
    private boolean isTableExsit(Connection connection )
    {
        try
        {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_LOGS_TABLE);
            while (!resultSet.next())
            {
                resultSet.close();
                resultSet = null;
                statement.executeQuery(CREATE_LOGS_TABLE);
            }
            return true;
        }
        catch (SQLException e)
        {
            LoggerManager.record(LoggerType.ERROR,"BaseInit sql error");
        }
        return false;
    }




    /**
     *  插入日志信息
     * @param connection
     * @param changeLogs
     */
    private void insertLogs(Connection connection, String...changeLogs)
    {
        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i < changeLogs.length; i++)
        {
            if(i == 0)
            {
                stringBuilder.append("(?,?)");
            }
            else
            {
                stringBuilder.append(",(?,?)");
            }
        }
        try
        {

            String sql = String.format(INSERT_LOGS,stringBuilder.toString());
            PreparedStatement statement = connection.prepareStatement(sql);

            for(int i = 0; i < changeLogs.length; i++)
            {
                int j = i * 2;
                statement.setString(j ,changeLogs[i]);
                statement.setLong(j+1, TIME);
            }
            //返回的应该是执行的条数。
            ResultSet resultSet = statement.executeQuery();

        }
        catch (SQLException e)
        {
            LoggerManager.record(LoggerType.ERROR,"BaseInit delete sql error");
        }
    }

    /**
     * 删除日志信息
     * @param connection
     * @param changeLogs
     */
    private void deleteLog(Connection connection, String...changeLogs)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");

        for(int i = 0; i < changeLogs.length; i++)
        {
            if(i == 0)
            {
                stringBuilder.append("?");
            }
            else
            {
                stringBuilder.append(",?");
            }
        }

        stringBuilder.append(")");


        try
        {

            String sql = String.format(DELETE_LOGS,stringBuilder.toString());
            PreparedStatement statement = connection.prepareStatement(sql);

            for(int i = 0; i < changeLogs.length; i++)
            {
                statement.setString(i,changeLogs[i]);
            }
            //返回的应该是执行的条数。
            ResultSet resultSet = statement.executeQuery();

        }
        catch (SQLException e)
        {
            LoggerManager.record(LoggerType.ERROR,"BaseInit delete sql error");
        }
    }

    /**
     * 关闭connetion
     * @param connection
     */
    private void closeConnection(Connection connection)
    {
        try
        {
            if (connection != null)
            {
                connection.close();
            }
        }
        catch (SQLException e)
        {
            LoggerManager.record(LoggerType.WARN,"Close statements failed :" + e.toString());
        }
    }
}
