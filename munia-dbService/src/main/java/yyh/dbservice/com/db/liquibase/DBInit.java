package yyh.dbservice.com.db.liquibase;

import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import yyh.dbservice.com.db.model.EvaCache;
import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by oak on 2017/9/4.
 */
public class DBInit extends SpringLiquibase
{


    private static final Long TIME = System.currentTimeMillis();


    private String CREATE_LOGS_TABLE = "create table databaseupdatelogs " +
            "('id' bigint(20) not null auto_increment comment 'id'," +
            "'path' varchar(1024) default '' comment 'path of changeset,'"+
            "'time' bigint (20) default 0 comment 'time when update'," +
            "primary key('id'))" +
            "engine = InnoDB default charset= utf8";

    private String QUERY_LOGS_TABLE = "show tabls like 'databaseupdatelogs'";

    private String DELETE_LOGS = "delete from 'databaseupdatelogs' where path in %s";

    private String INSERT_LOGS = "insert into 'databaseupdatelogs'(path,'time') value %s";

    private String QUERY_UPDATA_LOGS = "Select count(*) from 'databaseupdatelogs' where path in %s and 'time'  > %s";

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
        updataTime(projectId, changeLogs);
    }

    /**
     * 更新初始化的时间
     * @param projectId
     * @param changeLogs
     */
    private void updataTime(Long projectId, String...changeLogs)
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

        if(connection != null && isTableExsit(connection))
        {

            deleteLog(connection, changeLogs);
            insertLogs(connection, changeLogs);
        }

        closeConnection(connection);
    }


    /**
     *  插入日志信息
      * @param connection
     * @param changeLog
     */
    private void insertLogs(Connection connection, String...changeLog)
    {


    }

    /**
     * 删除日志信息
      * @param connection
     * @param changeLog
     */
    private void deleteLog(Connection connection, String...changeLog)
    {

    }

    /**
     * 检测liquibase数据库是否存在
     * @return
     */
    public boolean isTableExsit(Connection connection )
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
            LoggerManager.record(LoggerType.ERROR,"DBInit sql error");
        }
        return false;
    }

    /**
     * 检测数据库更新是否超期
     * @return
     */
    public boolean isOutTime()
    {


        return false;
    }

    /**
     * 删除liqui基础数据库
     */
    public void deleteLogTable()
    {

    }

    /**
     * 添加更新日志到数据库中
     */
    public void insertLogToTable(String...changeLogs)
    {



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
