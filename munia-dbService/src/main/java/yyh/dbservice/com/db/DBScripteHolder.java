package yyh.dbservice.com.db;

import yyh.dbservice.com.db.liquibase.BaseInit;
import yyh.dbservice.com.db.model.DBProperties;
import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库创建类
 * Created by oak on 2017/8/28.
 */
public class DBScripteHolder
{
    private DynamicDataSource dataSource;


    private BaseInit baseInit;

    /**
     * 创建数据库
     * @param dbProperties
     * @return
     */
    public List<String> getDBScript(DBProperties dbProperties)
    {
        creatDataBase(dbProperties);
        //此处后续添加liquing升级数据库方法

        return new ArrayList<>(1);
    }


    /**
     * 创建数据库
     * @param dbProperties
     * @return
     */
    private boolean creatDataBase(DBProperties dbProperties)
    {
        Connection con = null;
        Statement statement = null;
        try
        {
            con = dataSource.createDataBase(dbProperties);
            statement = con.createStatement();
            statement.execute(String.format("CREATE DATABASE %s;",dbProperties.getDbName()));
            statement.close();
            statement = null;
            con.close();
            con = null;
            return true;

        } catch (SQLException e)
        {
            LoggerManager.record(LoggerType.ERROR,"can not connect d");
        }
        finally
        {
            closeQuietly(statement);
            closeQuietly(con);
        }
        return false;
    }


    /**
     *
     * 关闭数据库的连接
     * @param con
     *
     */
    private void closeQuietly(AutoCloseable con)
    {
        if(con == null)
        {
            return;
        }

        try
        {
            con.close();

        }
        catch (Exception e)
        {

            LoggerManager.record(LoggerType.WARN,"Close Connection failed");

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

    public BaseInit getBaseInit()
    {
        return baseInit;
    }

    public void setBaseInit(BaseInit baseInit)
    {
        this.baseInit = baseInit;
    }
}
