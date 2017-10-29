package yyh.munia.parser.parser.model;

import org.apache.commons.lang3.StringUtils;
import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;
import yyh.munia.parser.parser.ConfigValueGet;
import yyh.munia.parser.parser.inf.IAfterInit;
import yyh.munia.parser.parser.inf.IBeforeInit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

/**
 *
 * 延迟是实例化类，
 *
 * Created by oak on 2017/10/27.
 */
public class ListLazyHashMap<T> extends HashMap<String,List<T>>
{

    /**
     * 存放信号量，用于锁住对象
     */
    private Map<String,Semaphore> semaphoreMap = new HashMap<>();

    /**
     * 已经实力话的handler对象
     */
    private IAfterInit<T> iAfterInit;

    private IBeforeInit<T> iBeforeInit;

    /**
     * 存放的处理类
     */
    private Map<String,List<ConfigValueGet>> confMap = new HashMap<>();

    /**
     *
     * @param iBeforeInit
     * @param iAfterInit
     */
    public ListLazyHashMap(IBeforeInit<T> iBeforeInit, IAfterInit<T> iAfterInit)
    {
        this.iBeforeInit = iBeforeInit;
        this.iAfterInit = iAfterInit;
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
        if (!super.containsKey(value.getKey()))
        {
            confMap.put(value.getKey(),new ArrayList<>());
        }
        confMap.get(value.getKey()).add(value);
        return value;
    }


    /**
     * 获取对象
     * @param key
     * @return
     */
    public List<T> get(Map<String,Object> def, String key)
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
            List<ConfigValueGet> configValueGets = confMap.get(key);
            super.put(key,new ArrayList<>(configValueGets.size()));
            for (ConfigValueGet configValueGet : configValueGets)
            {

                T iHandler = (T)configValueGet.getValue(def,"0");
                if (this.iBeforeInit.beforeInit(iHandler))
                {
                    LoggerManager.record(LoggerType.ERROR, "before init failed, key is" + key);
                }

                super.get(key).add(iHandler);
                if (iAfterInit != null && iAfterInit.afterInit((T) iHandler))
                {
                    LoggerManager.record(LoggerType.ERROR, "after init failed, key is" + key);
                }
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
