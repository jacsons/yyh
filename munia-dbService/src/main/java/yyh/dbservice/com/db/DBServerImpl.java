package yyh.dbservice.com.db;

import org.apache.commons.lang3.StringUtils;
import yyh.dbservice.com.db.error.DBException;
import yyh.dbservice.com.db.model.DBProperties;
import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;
import yyh.munia.com.util.redis.util.RedisUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 与redis打交道的类
 * Created by oak on 2017/8/9.
 */
public class DBServerImpl
{


    /**
     * KEY_DB_MYSQL_MAP : mysql ip : mysql与端口的对应关系
     * KEY_PROJECT_SERVER_MAP ： projectid 与 dbname
     * <p>
     * KEY_PROJECT_SERVER_MAP ：  projectid 与 ip之间的给关系
     *
     * KEY_MYSQL_PORT_MAP ： mysqlIP 与端口的对应关系，这个要预先配置好。
     */


    int indexId = 2;

    /**
     * 过去可用的ip
     *
     * @param projectID
     * @return
     */
    public String getProjectIP(String projectID) throws DBException
    {
        if (StringUtils.isEmpty(projectID))
        {
            throw new RuntimeException("projectId is null");
        }

        if (RedisUtil.hexists(indexId, "KEY_PROJECT_SERVER_MAP", projectID))
        {
            return RedisUtil.hget(indexId, "KEY_PROJECT_SERVER_MAP", projectID);
        } else
        {
            LoggerManager.record(LoggerType.ERROR, projectID + "don't exist");
            throw new DBException(projectID + "don't exist");
        }
    }

    /**
     * 获取数据库的端口
     *
     * @param serverip
     * @return
     */
    public String getDBPort(String serverip) throws DBException
    {
        if (RedisUtil.hexists(indexId, "KEY_DB_MYSQL_MAP", serverip))
        {
            return RedisUtil.hget(indexId, "KEY_DB_MYSQL_MAP", serverip);
        } else
        {
            LoggerManager.record(LoggerType.ERROR, serverip + "don't exist");
            throw new DBException(serverip + "don't exist");

        }
    }

    /**
     * 获取可用的数据库，有一个算法可以实现，这个先返回本地ip
     *
     * @return
     */
    public String allocateIP()
    {
        return "127.0.0.1";
    }

    /**
     * 返回数据库的名称
     *
     * @param projectID
     * @return
     */
    public String getDBName(String projectID) throws DBException
    {
        if (RedisUtil.hexists(indexId, "KEY_PROJECT_NAME_MAP", projectID))
        {
            return RedisUtil.hget(indexId, "KEY_PROJECT_NAME_MAP", projectID);
        } else
        {
            LoggerManager.record(LoggerType.ERROR, projectID + "don't exist");
            throw new DBException(projectID + "don't exist");
        }

    }


    /**
     * 获取数据库中所有的project信息
     *
     * @return
     */
    public List<DBProperties> getDBProperties()
    {
        List<DBProperties> result = new ArrayList<>();






        return result;
    }

    /**
     * 创建项目
     *
     * @param projectID
     * @return
     */
    public DBProperties createProject(String projectID) throws DBException
    {
        if (StringUtils.isEmpty(projectID))
        {
            throw new RuntimeException("projectId is null");
        }

        DBProperties dbProperties = new DBProperties();


        boolean ret = RedisUtil.hexists(indexId, "KEY_PROJECT_NAME_MAP", projectID);
        if (!ret)
        {
            String dbName = String.valueOf(System.currentTimeMillis());

            String ip = allocateIP();
            String port = RedisUtil.hget(indexId,"KEY_MYSQL_PORT_MAP",ip);
            RedisUtil.hset(indexId, "KEY_PROJECT_NAME_MAP", projectID,dbName);
            RedisUtil.hset(indexId, "KEY_PROJECT_SERVER_MAP", projectID,ip);
            RedisUtil.hset(indexId, "KEY_DB_MYSQL_MAP", ip,port);

            LoggerManager.record(LoggerType.INFO,"Create project successed, projectID : " + projectID);
        }
        else
        {

            LoggerManager.record(LoggerType.WARN, projectID + "Already exsit");
        }
        dbProperties.setDbName(RedisUtil.hget(indexId, "KEY_PROJECT_NAME_MAP", projectID));
        String dbIp = getProjectIP(projectID);
        dbProperties.setIp(dbIp);
        dbProperties.setPort(getDBPort(dbIp));
        return dbProperties;
    }

    /**
     * 删除reids中相关的数据信息
     * @param projectID
     * @return
     */
    public DBProperties deleteProject(String projectID) throws DBException
    {
        if(StringUtils.isEmpty(projectID))
        {
            throw new DBException("projectId is null");
        }

        if(!isExsitProject(projectID))
        {
            throw new DBException("projectId is null");
        }
        DBProperties dbProperties = getProjectInfo(projectID);
        RedisUtil.hdel(indexId,"KEY_PROJECT_NAME_MAP",projectID);
        RedisUtil.hdel(indexId,"KEY_PROJECT_SERVER_MAP",projectID);
        RedisUtil.hdel(indexId,"KEY_DB_MYSQL_MAP",dbProperties.getIp());

        return dbProperties;
    }

    /**
     * 查询项目是否存在
     * @param projectID
     * @return
     */
    public boolean isExsitProject(String projectID) throws DBException
    {
        if(StringUtils.isEmpty(projectID))
        {
            throw new DBException("projectId is null");
        }

        return RedisUtil.hexists(indexId, "KEY_PROJECT_NAME_MAP", projectID);
    }

    /**
     * 获得
     * @param projectID
     * @return
     */
    private DBProperties getProjectInfo(String projectID) throws DBException
    {
        DBProperties dbProperties = new DBProperties();

        String dbIp = getProjectIP(projectID);
        dbProperties.setIp(getProjectIP(dbIp));
        dbProperties.setPort(getDBPort(dbIp));
        return dbProperties;
    }

}
