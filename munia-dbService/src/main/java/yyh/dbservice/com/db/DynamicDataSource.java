package yyh.dbservice.com.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import yyh.dbservice.com.db.error.DBException;
import yyh.dbservice.com.db.model.DBProperties;
import yyh.dbservice.com.db.model.EvaCache;
import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 *
 * Created by oak on 2017/8/4.
 */
public class DynamicDataSource extends BasicDataSource
{
    private DataSourceFactory dataSourceFactory;

    private ProjectSourceDBMgr projectMgr;


    /**
     * 获取连接
     * @return
     * @throws SQLException
     */
    @Override
    public Connection getConnection() throws SQLException
    {

        DBProperties dbProperties = null;
        try
        {
            dbProperties = getProjectDBProperties();
        }
        catch (DBException e)
        {
            throw new SQLException("get getProjectDBProperties failed");
        }

        ComboPooledDataSource cp3d = dataSourceFactory.getComboPooledDataSource(dbProperties);
        if(cp3d == null)
        {
            LoggerManager.record(LoggerType.ERROR,"cannot get cp3d, projectID : " + dbProperties.getProjectID());
            throw new SQLException("cannot get cp3d");
        }
        return cp3d.getConnection();
    }

    /**
     * 获取连接
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    @Override
    public Connection getConnection(String username, String password) throws SQLException
    {
        DBProperties dbProperties = null;

        try
        {
            dbProperties = getProjectDBProperties();
        }
        catch (DBException e)
        {
            throw new SQLException("get getProjectDBProperties failed");
        }

        ComboPooledDataSource cp3d = dataSourceFactory.getComboPooledDataSource(dbProperties);

        if(cp3d == null)
        {
            LoggerManager.record(LoggerType.ERROR,"cannot get cp3d, projectID : " + dbProperties.getProjectID());
            throw new SQLException("cannot get cp3d");
        }
        return cp3d.getConnection(username,password);
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException
    {
        return null;
    }


    /**
     *
     * @return
     */
    private DBProperties getProjectDBProperties() throws DBException
    {
        String projectID = EvaCache.getPorjectId();

        return projectMgr.getDBProperties(projectID);
    }

    public DataSourceFactory getSessionManager()
    {
        return dataSourceFactory;
    }

    public void setSessionManager(DataSourceFactory dataSourceFactory)
    {
        this.dataSourceFactory = dataSourceFactory;
    }

    public ProjectSourceDBMgr getProjectMgr()
    {
        return projectMgr;
    }

    public void setProjectMgr(ProjectSourceDBMgr projectMgr)
    {
        this.projectMgr = projectMgr;
    }
}
