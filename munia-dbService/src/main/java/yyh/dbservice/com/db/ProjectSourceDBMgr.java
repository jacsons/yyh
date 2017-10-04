package yyh.dbservice.com.db;


import yyh.dbservice.com.db.error.DBException;
import yyh.dbservice.com.db.model.DBProperties;
import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目管理类，存储项目与数据库中的项目对应关系
 * Created by oak on 2017/8/4.
 */
public class ProjectSourceDBMgr
{

    /**
     * 数据库的前缀
     */
    private static final String PROJECT_PREFEX = "YYH_";

    /**
     *
     * 静态数据库名称
     *
     */
    private static final String SYSTEM_CONFIG_DB = "YYH_0";

    /**
     * 记录项目ID与数据库中项目id的对应关系
     */
    private Map<String, DBProperties> projectMapping = new HashMap<>();

    /**
     * 获取数据库中的项目id
     *
     * @param projectID
     * @return
     */
    public DBProperties getDBProperties(String projectID) throws DBException
    {
        String projectKey = DBFactory.getInstance().getProjectName(projectID);
        DBProperties db = projectMapping.get(projectKey);

        //项目id未找到
        if (db == null)
        {
            return SYSTEM_CONFIG_DB.equals(projectKey) ? initSystemDB(projectKey) : getProjectProperite(projectKey);
        }
        return db;
    }

    /**
     * 获取项目
     *
     * @param projectID
     * @return
     */
    private String getProjectId(String projectID)
    {
        return String.format("%s%s", PROJECT_PREFEX, projectID);
    }


    /**
     * 初始化静态数据库
     *
     * @param projectKey
     * @return
     */
    private DBProperties initSystemDB(String projectKey)
    {
        DBProperties db = null;


        return db;
    }

    /**
     * 获取项目信息
     *
     * @param projectKey
     * @return
     */
    private DBProperties getProjectProperite(String projectKey) throws DBException
    {

        DBProperties dbProperties = DBFactory.getInstance().getProjectPropertie(projectKey);
        if (dbProperties == null)
        {
            LoggerManager.record(LoggerType.WARN, projectKey + " is not exsit");
            throw new DBException(projectKey + " is not exsit");
        }
        dbProperties.setDbName(DBFactory.getInstance().getDBname(dbProperties));
        projectMapping.put(projectKey, dbProperties);
        return dbProperties;
    }
}
