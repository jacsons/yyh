package yyh.munia.com.util.redis.util;

import redis.clients.jedis.Jedis;
import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 *
 * Redis 工具类（外部使用一律使用该类接口）
 *
 * 该工具的读写，分为字符串的读写，数据的读写，（实现jedis支持的读写功能）
 *
 * Created by oak on 2017/7/27.
 */
public class RedisUtil
{

    /**
     *  redis操作接口
     * @param indexID
     * @param modelKey
     * @param filed
     * @param value
     */
    public synchronized static void hset(int indexID,String modelKey,String filed,String value)
    {

        operator(indexID,getMethod("hset",String.class,String.class,String.class),modelKey,filed,value);
    }

    /**
     * redis操作接口
     * @param indexID
     * @param modelKey
     * @param filed
     */
    public synchronized static String hget(int indexID,String modelKey,String filed)
    {

        return (String) operator(indexID,getMethod("hget",String.class,String.class),modelKey,filed);
    }

    /**
     * 获取文件操作
     * @param indexId
     * @param taskKey
     * @return
     */
    public synchronized static String hget(int indexId, String taskKey)
    {
        return hget(indexId, taskKey);
    }


    /**
     * 内容写操作
     * @param indexId
     * @param taskKey
     */
    public synchronized static void hset(int indexId, String taskKey, String modelKey)
    {
        hset(indexId, taskKey, modelKey, "");
    }


    /**
     * 检查是否存在
     * @param indexID
     * @param modelKey
     * @param filed
     * @return
     */
    public synchronized static boolean hexists(int indexID,String modelKey,String filed)
    {
        return (boolean) operator(indexID,getMethod("hexists",String.class,String.class),modelKey,filed);
    }

    /**
     * 删除modelKey filed
     * @param indexID
     * @param modelKey
     * @param filed
     * @return
     */
    public synchronized static boolean hdel(int indexID,String modelKey,String filed)
    {
        return (boolean) operator(indexID,getMethod("hdel",String.class,String.class),modelKey,filed);
    }

    /**
     * 获取modelKey下所有的key value对
     * @param indexID
     * @param modelKey
     * @return
     */
    public synchronized static Set<String> hkeys(int indexID,String modelKey)
    {
        return (Set<String>)operator(indexID,getMethod("hkeys",String.class),modelKey);
    }


    /**
     * 操作redis hash get方法
     * @param indexID  选择jedisId
     * @param methodName 执行jedis方法
     */
    private static Object operator(int indexID,Method methodName,String...parm)
    {
        Jedis jedis = getJedis(indexID);

        if(jedis == null || methodName == null)
        {
            return null;
        }
        try
        {
            Method[] methods = Jedis.class.getMethods();
            for(Method method : methods)
            {
                if(method.equals(methodName))
                {
                    return method.invoke(jedis,parm);
                }
            }

        }
         catch (InvocationTargetException e)
        {

            LoggerManager.record(LoggerType.ERROR,e.toString());

        } catch (IllegalAccessException e)
        {

            LoggerManager.record(LoggerType.ERROR,e.toString());

        }
        finally
        {

            //此时需要返回resource
            RedisPoolsUtil.close(jedis);
        }
        return null;
    }


    private static Method getMethod(String methodName,Class<?>...parm)
    {
        Method method = null;
        try
        {
            method = Jedis.class.getMethod(methodName,parm);
        } catch (NoSuchMethodException e)
        {
            LoggerManager.record(LoggerType.INFO,"RedisUtil getMethod not find method");
        }

        return method;
    }

    /**
     * 获取jedis
     * @param index
     * @return
     */
    private static Jedis getJedis(Integer index)
    {
        Jedis jedis = RedisPoolsUtil.getJedis();
        if(jedis != null)
        {
            jedis.select(index);
            return jedis;
        }
        return null;
    }

}
