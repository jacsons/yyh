package yyh.munia.com.prgress.impl;

import yyh.munia.com.prgress.inf.IProgressOperator;
import yyh.munia.com.util.redis.util.RedisUtil;

/**
 * 操作数据库的接口
 * Created by oak on 2017/9/15.
 */
public class ProgressRedisOperator implements IProgressOperator
{

    private String taskKey;

    private int redisIIndexId;

    /**
     * 进度读操作
     * @param taskKey
     * @param reidsIndexId
     */
    public ProgressRedisOperator(String taskKey, int reidsIndexId)
    {
        this.taskKey = taskKey;
        this.redisIIndexId = reidsIndexId;
    }


    /**
     * 写操作
     * @param modelKey
     * @param value
     */
    public synchronized void write(String modelKey, String value)
    {
        RedisUtil.hset(this.redisIIndexId, getTaskKey(), modelKey, value);
    }

    /**
     * 读进度信息
     * @param modelKey
     * @return
     */
    public synchronized String read(String modelKey)
    {
       return RedisUtil.hget(this.redisIIndexId, getTaskKey(), modelKey);
    }

    /**
     * 删除进度信息
     * @param modelKey
     * @return
     */
    public synchronized String clear(String modelKey)
    {
        String deleteProgress = read(modelKey);
        RedisUtil.hdel(this.redisIIndexId, getTaskKey(), modelKey);
        return deleteProgress;
    }

    public String getTaskKey()
    {
        return taskKey;
    }

    public void setTaskKey(String taskKey)
    {
        this.taskKey = taskKey;
    }

    public int getRedisIIndexId()
    {
        return redisIIndexId;
    }

    public void setRedisIIndexId(int redisIIndexId)
    {
        this.redisIIndexId = redisIIndexId;
    }
}
