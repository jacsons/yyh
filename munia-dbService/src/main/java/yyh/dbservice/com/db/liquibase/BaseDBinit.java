package yyh.dbservice.com.db.liquibase;

import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import liquibase.util.NetUtil;
import yyh.dbservice.com.db.DBServerImpl;
import yyh.dbservice.com.db.DynamicDataSource;
import yyh.dbservice.com.db.IOCloseUtil;
import yyh.dbservice.com.db.model.DBProperties;
import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * 初始化
 * Created by oak on 2017/9/13.
 */
public class BaseDBinit extends SpringLiquibase
{
    /**
     * 提供给其他地方用的锁
     */
    public static String lock;

    static
    {

        try
        {
            lock = String.format("%s%s", NetUtil.getLocalHostAddress(), NetUtil.getLocalHostName());
        }
        catch (UnknownHostException e)
        {
            LoggerManager.record(LoggerType.ERROR, "BaseInit lock init UnknownHostException error");
        }
        catch (SocketException e)
        {
            LoggerManager.record(LoggerType.ERROR, "BaseInit lock init SocketException error");
        }

    }

    /**
     * 记录存储liquibase数据库名称的
     */
    private static final String DB_NAME = "yyh_liqui_DB";

    /**
     * 创建数据库的sql
     */
    private static final String SQL_CREAT = "create database if not exists %s default charset utf8";

    /**
     * 保证mysql一定存在，这个的意义在与，yyh_liqui_DB不存在时，先连接到mysql创建数据库。
     */
    private static final String MYSQL = "MYSQL";


    private DynamicDataSource dataSource;

    /**
     * 更新数据库
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws LiquibaseException
    {
        DBServerImpl dbServer = new DBServerImpl();

        /**
         * 获取所有的ip 端口 信息
         */
        List<DBProperties> dbProperties = dbServer.getDBProperties();
        for (DBProperties dbPropertie : dbProperties)
        {
            updateDB(dbPropertie);
        }
    }

    public DynamicDataSource getDataSource()
    {
        return dataSource;
    }

    public void setDataSource(DynamicDataSource dataSource)
    {
        this.dataSource = dataSource;
    }


    /**
     * 更新DB,其实是创建db数据库
     */
    private void updateDB(DBProperties dbPropertie) throws LiquibaseException
    {
        //确认下这个连接是哪里来的
        Connection connection = null;
        Statement statement = null;
        try
        {
            connection = this.getDataSource().createDataBase(dbPropertie);
        }
        catch (SQLException e)
        {
            LoggerManager.record(LoggerType.ERROR, "BaseDBinit updateDB getConnection failed");
        }

        //说明yyh_liqui_db不存在
        if (connection == null)
        {

            //连接到MYSQL上。

            try
            {
                connection = this.getDataSource().createDataBase(dbPropertie);

                if(connection == null)
                {
                    LoggerManager.record(LoggerType.ERROR, "BaseDBinit updateDB getConnection MYSQL failed");

                }

                statement = connection.createStatement();
                statement.execute(String.format(SQL_CREAT, DB_NAME));
            }
            catch (SQLException e)
            {
                LoggerManager.record(LoggerType.ERROR, "BaseDBinit updateDB getConnection MYSQL failed");
            }

            IOCloseUtil.close(statement);
            IOCloseUtil.close(connection);
        }
        super.afterPropertiesSet();
    }

    /**
     * 获取数据的url链接
     *
     * @param ip
     * @param port
     * @param dbName
     * @return
     */
    private String getDBURL(String ip, String port, String dbName)
    {
        //多查询语句是怎么写的
        String head = String.format("jdbc:mysql://%s:%s/%s", ip, port, dbName);
        String url = head + "?useUnicode=true&amp;characterEncoding=utf-8&useSSL=false&allowMultiQueries=true";
        return url;
    }
}
