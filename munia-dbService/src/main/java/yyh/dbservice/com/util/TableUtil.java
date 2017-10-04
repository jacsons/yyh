package yyh.dbservice.com.util;

import org.springframework.beans.factory.annotation.Autowired;
import yyh.dbservice.com.db.liquibase.BaseInit;
import yyh.dbservice.com.db.model.EvaCache;

/**
 * 表哥处理类
 * Created by oak on 2017/9/24.
 */
public class TableUtil
{
    /**
     * 数据库
     */
    private String[] chaneLogs;

    @Autowired
    private BaseInit baseInit;

    /**
     * 创建数据库表
     */
    public void createTable()
    {
        String project = EvaCache.getPorjectId();

        if (baseInit.isOutTime(project,chaneLogs))
        {
            baseInit.updata(project, chaneLogs);
        }
    }

    public String[] getChaneLogs()
    {
        return chaneLogs;
    }

    public void setChaneLogs(String[] chaneLogs)
    {
        this.chaneLogs = chaneLogs;
    }
}
