package yyh.munia.parser.parser;

import org.apache.commons.lang3.StringUtils;
import yyh.munia.com.SpringBeanUtil;
import yyh.munia.com.classrelative.ClassUtil;
import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * config配置类
 * Created by oak on 2017/10/14.
 */
public class ConfigValueGet
{

    /**
     * Spring对象
     */
    private static final Pattern SPRING = Pattern.compile("^spring\\((\\w+)\\)$");

    /**
     * 参数
     */
    private static final Pattern PARAMS = Pattern.compile("^@(\\w+)[\\s]*$");

    /**
     *
     */
    private static final Pattern VALUE_CONSTRUCTOR = Pattern.compile("^([^\\s\\(\\)]+)\\(((?:[^,\\s]+[,\\s]*)*)\\)$");

    /**
     * key value
     */
    private String key;

    /**
     *
     */
    private ValueGet valueGet;


    public ConfigValueGet(String key, String value)
    {
        this.key = key;
        this.valueGet = parserValueGet(value);
    }


    public Object getValue(Map<String, Object> def, String projectId)
    {
        return this.valueGet.getValue(def, projectId);
    }
    /**
     * 根据key 解析初Value对象
     * @param value
     * @return
     */
    private ValueGet parserValueGet(String value)
    {
        if (value == null || value.trim().isEmpty())
        {
            throw new RuntimeException("the value cannot be null");
        }


        Matcher matcher = PARAMS.matcher(value);
        if (matcher.find())
        {
            return new VarGet(matcher.group(0));
        }

        matcher = SPRING.matcher(value);

        if (matcher.find())
        {
            return new SpringGet(matcher.group(0));
        }

        matcher = VALUE_CONSTRUCTOR.matcher(value);
        if (matcher.find())
        {
            try
            {
                return new ConstructGet(matcher.group(1), matcher.group(2));
            }
            catch (ClassNotFoundException e)
            {
                throw new RuntimeException("the configue valie [" + value + "] can not parsed to Class");
            }
        }

        return new VarGet(value);
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public ValueGet getValueGet()
    {
        return valueGet;
    }

    public void setValueGet(ValueGet valueGet)
    {
        this.valueGet = valueGet;
    }

    /**
     * 获取对象的顶层类
     */
    private interface ValueGet
    {

        Object getValue(Map<String, Object> def, String projectID);

    }

    private class VarGet implements ValueGet
    {
        private String value;

        public VarGet(String value)
        {
            this.value = value;
        }


        @Override
        public Object getValue(Map<String, Object> def, String projectID)
        {
            return def.get(this.value);
        }
    }



    /**
     * 获取spring对象
     */
    private class SpringGet implements ValueGet
    {

        private String value;

        public SpringGet(String value)
        {
            this.value = value;
        }

        @Override
        public ValueGet getValue(Map<String, Object> def, String projectID)
        {
            return SpringBeanUtil.getBean(this.value, ValueGet.class);
        }
    }

    /**
     * 更具构造函数获取对象
     */
    private class ConstructGet implements ValueGet
    {
        /**
         * value值
         */
        private Class value;

        /**
         * value对应的参数值
         */
        private ValueGet[] valueGets;

        /**
         * 构 造 函 数
         * @param value
         * @param param
         * @throws ClassNotFoundException
         */
        public ConstructGet(String value, String param) throws ClassNotFoundException
        {
            this.value = Class.forName(value);

            if (!StringUtils.isEmpty(param))
            {
                valueGets = new ValueGet[0];
                return;
            }

            String[] params = param.split("^\\s,\\s");
            valueGets = new ValueGet[params.length];

            for (int i = 0; i < params.length; ++i)
            {
                valueGets[i] = parserValueGet(params[i]);
            }

        }


        @Override
        public Object getValue(Map<String, Object> def, String projectID)
        {
            //根据construt获取对应的
            Object[] params = new Object[valueGets.length];
            for (int i = 0; i < valueGets.length; ++i)
            {
                params[i] = valueGet.getValue(def, projectID);
            }

            Constructor[] constructors = this.value.getConstructors();

            try
            {
                for (Constructor constructor : constructors)
                {
                    //获取构造函数的参数
                    Class[] parameters = constructor.getParameterTypes();
                    if (ClassUtil.typeCheck(parameters, params))
                    {
                        return constructor.newInstance(params);
                    }

                }
            }
            catch (InstantiationException | IllegalAccessException | InvocationTargetException e)
            {
                LoggerManager.record(LoggerType.ERROR,"ConstructGet error" + this.value);
            }

            throw new RuntimeException(this.value + "not find");
        }
    }
}
