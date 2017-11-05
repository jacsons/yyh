package yyh.munia.parser.parser.model;

import org.apache.commons.lang3.StringUtils;
import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;
import yyh.munia.parser.parser.ConfigValueGet;
import yyh.munia.parser.parser.inf.IAfterInit;
import yyh.munia.parser.parser.inf.IBeforeInit;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

/**
 * 存放单个的处理器的hashMap
 * Created by oak on 2017/10/29.
 */
public class LazyHashMap<T> extends HashMap<String,T>
{
    /**
     * 存放信号量，用于锁住对象
     */
    private Map<String,Semaphore> semaphoreMap = new HashMap<>();


    /**
     * 存放的处理类
     */
    private Map<String,T> hanlderMap = new HashMap<>();

    /**
     * 已经实力话的handler对象
     */
    private IAfterInit<T> iAfterInit;

    private IBeforeInit<T> iBeforeInit;


    /**
     * 存放的处理类
     */
    private Map<String,ConfigValueGet> confMap = new HashMap<>();

    /**
     *
     */
    private Map<String,Object> def;

    /**
     *
     * @param iBeforeInit
     *        获取实例前的处理器
     * @param iAfterInit
     *        获取实例后的处理器
     */
    public LazyHashMap(Map<String,Object> def, IBeforeInit<T> iBeforeInit, IAfterInit<T> iAfterInit)
    {
        this.iBeforeInit = iBeforeInit;
        this.iAfterInit = iAfterInit;
        this.def = def;
    }

    /**
     *
     * @param value
     * @return
     */
    public ConfigValueGet put(ConfigValueGet value)
    {
        if (value != null && !StringUtils.isEmpty(value.getKey()))
        {
            Semaphore semaphore = new Semaphore(1);
            semaphoreMap.put(value.getKey(),semaphore);
        }
        if (super.containsKey(value.getKey()))
        {
            LoggerManager.record(LoggerType.WARN, "duplication key :" + value.getKey());
        }
        confMap.put(value.getKey(),value);
        return value;
    }


    /**
     * 获取对象
     * @param key
     * @return
     */
    public T get(Map<String,Object> def, String key)
    {
        if (StringUtils.isEmpty(key))
        {
            return null;
        }

        if (!super.containsKey(key))
        {
            return super.get(key);
        }

        //如果还没有拿到，那么得初始化了
        Semaphore semaphore = semaphoreMap.get(key);
        try
        {
            semaphore.acquire();
            if (!semaphoreMap.containsKey(key))
            {
                return super.get(key);
            }

            //执行行数主体
            ConfigValueGet configValueGet = confMap.get(key);

            T iHandler = (T)configValueGet.getValue(def,"0");
            if (this.iBeforeInit.beforeInit(iHandler))
            {
                LoggerManager.record(LoggerType.ERROR, "before init failed, key is" + key);
            }

            super.put(key, iHandler);
            if (iAfterInit != null && iAfterInit.afterInit((T) iHandler))
            {
                LoggerManager.record(LoggerType.ERROR, "after init failed, key is" + key);
            }
            super.remove(key);
        }
        catch (InterruptedException e)
        {
            LoggerManager.record(LoggerType.ERROR, "get va");
        }
        finally
        {
            semaphore.release();
        }

        return super.get(key);
    }

}
