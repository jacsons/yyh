package yyh.dbservice.com.db.model;

import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;

/**
 *
 * 存储副本单元，保存副本数据，用于线程中的读
 * Created by oak on 2017/7/29.
 */
public class EvaCache
{

    private static ThreadLocal<Monitor> threadLocal = new ThreadLocal<Monitor>();

    public static String getPorjectId()
    {
        if (threadLocal.get() == null)
        {
            LoggerManager.record(LoggerType.WARN,"project is null");
            return "0";
        }

        return threadLocal.get().getProjectId();
    }

    public  static void setProjectId(String projectId)
    {
        if(threadLocal.get() == null)
        {
            Monitor monitor = new Monitor();
            threadLocal.set(monitor);
        }
        threadLocal.get().setProjectId(projectId);
    }

    /**
     * 是否为新创建的数据库
     */
    public static boolean isNewDb()
    {
        boolean isNew = false;
        if(threadLocal.get() != null)
        {
            isNew = threadLocal.get().isNewDB();
        }

        return isNew;
    }

    public static void setNewDB(boolean newDb)
    {
        threadLocal.get().setNewDB(newDb);
    }
}

