package yyh.munia.com.util;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 *
 * Created by oak on 2017/9/15.
 */
public class GsonUtil
{
    private static Gson gson = new Gson();


    /**
     * 将对象转化为String
     * @param object
     * @return
     */
    public static <T> String gsonFrom(T object)
    {
        return gson.toJson(object);
    }


    /**
     * 将string转化为对象
     * @param json
     * @param typeOfT
     * @return
     */
    public static <T> T toObject(String json, Type typeOfT)
    {
        return gson.fromJson(json,typeOfT);
    }

}
