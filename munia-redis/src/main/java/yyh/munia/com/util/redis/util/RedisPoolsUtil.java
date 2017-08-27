package yyh.munia.com.util.redis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;


/**
 * Redis Pools 工具类，
 *
 * Created by oak on 2017/7/27.
 */
public class RedisPoolsUtil
{

    /**
     *
     */
    private static JedisPool jedisPool = null;

    /**
     *
     */
    private static String address = "127.0.0.1";

    /**
     *
     */
    private static int port = 6379;

    /**
     *
     */
    private static String auth = "admin";

    static
    {
        try
        {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(200);
            config.setMaxIdle(10);
            config.setMaxWaitMillis(10000);
            config.setTestOnBorrow(true);
            //Idle时进行连接扫描
            config.setTestWhileIdle(true);
            //表示idle object evitor两次扫描之间要sleep的毫秒数
            config.setTimeBetweenEvictionRunsMillis(30000);
            //表示idle object evitor每次扫描的最多的对象数
            config.setNumTestsPerEvictionRun(10);
            //表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
            config.setMinEvictableIdleTimeMillis(30000);

            jedisPool = new JedisPool(config, address, port, 10000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized static Jedis getJedis()
    {
        try
        {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                return resource;
            } else
            {
                return null;
            }
        } catch (Exception e)
        {
            LoggerManager.record(LoggerType.ERROR,"RedisPoolsUtil getJedis" + e.toString());
            return null;
        }
    }

    public static void close(final Jedis jedis)
    {
        if (jedis != null)
        {
            jedis.close();
        }
    }
}
