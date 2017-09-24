package yyh.dbservice.com.db.liquibase;

import org.springframework.jdbc.datasource.AbstractDataSource;
import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * datasource基础类
 * Created by oak on 2017/9/22.
 */
public class BasicConnection extends AbstractDataSource
{
    /**
     * 普通的连接方式
     */
    private static String driverClass = "com.mysql.jdbc.Driver";

    private String username;

    private String password;

    private String url;

    @Override
    public Connection getConnection() throws SQLException
    {
        return getConnection(getUsername(),getPassword());
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException
    {
        try
        {
            Class.forName(driverClass);
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
        }
        catch (ClassNotFoundException e)
        {
            LoggerManager.record(LoggerType.ERROR,"ClassNotFoundException error", e);
        }
        catch (SQLException e)
        {
            LoggerManager.record(LoggerType.ERROR,"SQLException error", e);
        }
        return null;
    }


    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }
}
