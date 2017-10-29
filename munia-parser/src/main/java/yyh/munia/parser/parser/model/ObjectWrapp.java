package yyh.munia.parser.parser.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 存储存储
 * Created by oak on 2017/10/16.
 */
public class ObjectWrapp
{
    /**
     * 存储对象value
     */
    private static Map<String,String> wrapMap = new ConcurrentHashMap<>();


    /**
     * 存储
     * @param key
     * @param clasName
     */
    public static void put(String key, String clasName)
    {
        wrapMap.put(key, clasName);
    }


}
