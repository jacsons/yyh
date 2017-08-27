package yyh.dbservice.com.db.model;

/**
 * Created by oak on 2017/8/1.
 */
public class ComboPooledConfig
{
    private String driverClass;

    private String jdbcUrl;

    private String user;

    private String password;

    private String maxPoolSize;

    private String minPoolSize;

    private String initialPoolSize;

    private String maxIdleTime;

    private String maxWaitTime;


    public String getDriverClass()
    {
        return driverClass;
    }

    public void setDriverClass(String driverClass)
    {
        this.driverClass = driverClass;
    }

    public String getJdbcUrl()
    {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl)
    {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getMaxPoolSize()
    {
        return maxPoolSize;
    }

    public void setMaxPoolSize(String maxPoolSize)
    {
        this.maxPoolSize = maxPoolSize;
    }

    public String getMinPoolSize()
    {
        return minPoolSize;
    }

    public void setMinPoolSize(String minPoolSize)
    {
        this.minPoolSize = minPoolSize;
    }

    public String getInitialPoolSize()
    {
        return initialPoolSize;
    }

    public void setInitialPoolSize(String initialPoolSize)
    {
        this.initialPoolSize = initialPoolSize;
    }

    public String getMaxIdleTime()
    {
        return maxIdleTime;
    }

    public void setMaxIdleTime(String maxIdleTime)
    {
        this.maxIdleTime = maxIdleTime;
    }

    public String getMaxWaitTime()
    {
        return maxWaitTime;
    }

    public void setMaxWaitTime(String maxWaitTime)
    {
        this.maxWaitTime = maxWaitTime;
    }
}
