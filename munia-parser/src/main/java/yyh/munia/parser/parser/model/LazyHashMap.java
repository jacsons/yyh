package yyh.munia.parser.parser.model;

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




}
