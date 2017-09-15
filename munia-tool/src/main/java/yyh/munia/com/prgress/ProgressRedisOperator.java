package yyh.munia.com.prgress;

import yyh.munia.com.util.redis.util.RedisUtil;

/**
 * 操作数据库的接口
 * Created by oak on 2017/9/15.
 */
public class ProgressRedisOperator
{

    private String taskKey;

    private int reidsIndexId;

    /**
     * 进度读操作
     * @param taskKey
     * @param reidsIndexId
     */
    public ProgressRedisOperator(String taskKey, int reidsIndexId)
    {
        this.taskKey = taskKey;
        this.reidsIndexId = reidsIndexId;
    }


    /**
     * 写操作
     * @param modelKey
     * @param value
     */
    public synchronized void hset(String modelKey, String value)
    {
        RedisUtil.hset(this.reidsIndexId, this.taskKey, modelKey, value);
    }

    /**
     * 读进度信息
     * @param modelKey
     * @return
     */
    public synchronized String hget(String modelKey)
    {
       return RedisUtil.hget(this.reidsIndexId, this.taskKey, modelKey);
    }


    public String getTaskKey()
    {
        return taskKey;
    }

    public void setTaskKey(String taskKey)
    {
        this.taskKey = taskKey;
    }

    public int getReidsIndexId()
    {
        return reidsIndexId;
    }

    public void setReidsIndexId(int reidsIndexId)
    {
        this.reidsIndexId = reidsIndexId;
    }
}
