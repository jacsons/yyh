package yyh.dbservice.com.db;

import org.apache.commons.lang3.StringUtils;
import yyh.dbservice.com.db.error.DBException;
import yyh.dbservice.com.db.model.DBProperties;
import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 与redis打交道，查询数据库
 * Created by oak on 2017/8/7.
 */
public class DBFactory
{

    private static final String PROJECT_PRFEX = "YYH_";

    private static DBFactory instance = null;

    /**
     * 获取实例
     * @return
     */
    public static DBFactory getInstance()
    {
        return LazeInitlazation.getInstance();
    }


    /**
     * 根据ip 获取数据库的信息
     * @param projectID
     * @return
     */
    public  DBProperties getProjectPropertie(String projectID) throws DBException
    {
        if (StringUtils.isEmpty(projectID) || !isExsitProject(projectID))
        {
            LoggerManager.record(LoggerType.WARN,"projectID is null");
            return null;
        }

        DBProperties dbProperties = new DBProperties();
        DBServerImpl dbServer = new DBServerImpl();
        String ip = dbServer.getProjectIP(projectID);
        String port = dbServer.getDBPort(ip);
        String dbName = dbServer.getDBName(projectID);

        dbProperties.setIp(ip);
        dbProperties.setPort(port);
        dbProperties.setDbName(dbName);
        dbProperties.setProjectID(projectID);

        return dbProperties;
    }


    /**
     * 创建信息的项目
     * @return
     */
    public  DBProperties creatNeProject(String projectID)
    {
        if (StringUtils.isEmpty(projectID))
        {
            LoggerManager.record(LoggerType.WARN,"projectID is null");
            return null;
        }
        DBServerImpl dbServer = new DBServerImpl();
        DBProperties dbProperties = null;
        try
        {
            dbProperties = dbServer.createProject(projectID);
        }
        catch (DBException e)
        {
            e.printStackTrace();
        }

        return dbProperties;
    }

    /**
     * 删除项目
     * @param projectID
     */
    public  DBProperties deleteProject(String projectID) throws DBException
    {
        if (StringUtils.isEmpty(projectID))
        {
            LoggerManager.record(LoggerType.WARN,"projectID is null");
            throw new DBException("projectID is null");
        }

        return new DBServerImpl().deleteProject(projectID);
    }

    public static List<DBProperties> getAllProjectInfo()
    {
        List<DBProperties> properties = new ArrayList<>();

        return properties;
    }

    /**
     * 查询数据库是否存在
     * @param projectID
     * @return
     */
    public  boolean isExsitProject(String projectID) throws DBException
    {
        if (StringUtils.isEmpty(projectID))
        {
            LoggerManager.record(LoggerType.WARN,"projectID is null");
            return false;
        }
        DBServerImpl dbServer = new DBServerImpl();
        return dbServer.isExsitProject(projectID);
    }


    public String getProjectName(String projectID)
    {
        return new StringBuilder().append("PROJECT_PRFEX").append(projectID).toString();
    }

    /**
     *
     * 迟滞实例化
     *
     */
    public static class LazeInitlazation
    {
        private static DBFactory dbFactory = new DBFactory();

        public static DBFactory getInstance()
        {
            return dbFactory;
        }
    }
}