package yyh.munia.com.util.redis.util;

/**
 *
 * reids执行的动作方式
 * Created by oak on 2017/7/27.
 */
public enum  RedisActionType
{
    StringType(1);
    private int id;

    RedisActionType(int id)
    {
        this.id = id;
    }
}
