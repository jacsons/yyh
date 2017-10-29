package yyh.munia.parser.parser.model;

import yyh.munia.parser.parser.inf.IAfterInit;
import yyh.munia.parser.parser.inf.IBeforeInit;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

/**
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
     *
     * @param iBeforeInit
     * @param iAfterInit
     */
    public LazyHashMap(IBeforeInit<T> iBeforeInit, IAfterInit<T> iAfterInit)
    {
        this.iBeforeInit = iBeforeInit;
        this.iAfterInit = iAfterInit;
    }



}
