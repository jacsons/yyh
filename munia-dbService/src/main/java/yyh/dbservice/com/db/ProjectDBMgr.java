package yyh.dbservice.com.db;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
import yyh.dbservice.com.db.error.DBException;
import yyh.dbservice.com.db.model.DBProperties;
import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by oak on 2017/8/14.
 */
public class ProjectDBMgr
{

    private BasicDataSource dataSource;

    /******************************
     *
     * 此处知识实现创建数据库，后面可以通过编写xml文件，然后
     *
     */

    /**
     * 创建数据库
     */
    private static final String DROP_DATABASE = "DROP DATABASE %s";

    /**
     * 删除数据库
     */
    private static final String CREATE_DATABASE = "CREATE DATABASE %s";

    /**
     * 创建项目
     * @param projectID
     */
    public DBProperties createProject(String projectID) throws DBException
    {
        if(StringUtils.isEmpty(projectID))
        {
            throw new DBException("projectID is null");
        }

        String projectName = DBFactory.getInstance().getProjectName(projectID);
        DBProperties dbProperties;
        DBServerImpl dbServer = new DBServerImpl();

        Boolean ret = dbServer.isExsitProject(projectID);

        //如果存在项目
        if(ret)
        {
            LoggerManager.record(LoggerType.INFO,"projectID exsit");
            dbProperties = DBFactory.getInstance().getProjectPropertie(projectName);
        }
        else
        {
            DBFactory.getInstance().creatNeProject(projectName);
            dbProperties = DBFactory.getInstance().getProjectPropertie(projectName);

            executeCreateProjectScript(dbProperties);
        }
        return dbProperties;
    }


    /**
     * 删除项目
     * @param projectID
     */
    public DBProperties deleteProject(String projectID) throws DBException
    {
        if(StringUtils.isEmpty(projectID))
        {
            throw new DBException("projectID is null");
        }

        String projectName = DBFactory.getInstance().getProjectName(projectID);
        DBProperties dbProperties = null;
        DBServerImpl dbServer = new DBServerImpl();
        Boolean ret = dbServer.isExsitProject(projectID);

        if(!ret)
        {
            LoggerManager.record(LoggerType.INFO,"projectID exsit");
            throw new DBException("projectID exsit");
        }
        else
        {
            dbProperties = DBFactory.getInstance().deleteProject(projectID);
            executeDeleteProjectScript(dbProperties);
        }

        return dbProperties;
    }

    /**
     * 执行删除脚本
     *
     */
    private void executeDeleteProjectScript(DBProperties dbProperties) throws DBException
    {
        String dbProjectName = String.format("%s_%s",dbProperties.getProjectID(),dbProperties.getDbName());
        Connection connection = null;
        Statement statements = null;
        try
        {
            connection = dataSource.getConnection();
            statements = connection.createStatement();
            statements.execute(String.format(DROP_DATABASE,dbProjectName));
        }
        catch (SQLException e)
        {
            LoggerManager.record(LoggerType.ERROR,"delete db failed");
        }
        finally
        {
            closeStatement(statements);
            closeConnection(connection);
        }

    }

    /**
     * 执行创建脚本
     * @param dbProperties
     * @throws DBException
     */
    private void executeCreateProjectScript(DBProperties dbProperties) throws DBException
    {
        Connection connection = null;
        Statement statements = null;
        try
        {
            connection = dataSource.getConnection();
            statements = connection.createStatement();
            statements.execute(String.format(CREATE_DATABASE,dbProperties.getDbName()));
            executeCreateProjectScript(dbProperties);
        }
        catch (SQLException e)
        {
            LoggerManager.record(LoggerType.ERROR,"create db failed");
            executeDeleteProjectScript(dbProperties);
            throw new DBException("create db failed");
        }
        finally
        {
            closeStatement(statements);
            closeConnection(connection);
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

    /**
     * 关闭Statement
     * @param statements
     */
    private void closeStatement(Statement statements)
    {
        try
        {
            if (statements != null)
            {
                statements.close();
            }
        }
        catch (SQLException e)
        {
            LoggerManager.record(LoggerType.WARN,"Close statements failed :" + e.toString());
        }
    }


    public BasicDataSource getDataSource()
    {
        return dataSource;
    }

    public void setDataSource(BasicDataSource dataSource)
    {
        this.dataSource = dataSource;
    }
}
