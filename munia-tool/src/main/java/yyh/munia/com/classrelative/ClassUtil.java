package yyh.munia.com.classrelative;

import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;
import yyh.munia.com.util.NullCheckUtil;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 与Class对象处理相关的类
 * Created by oak on 2017/10/15.
 */
public class ClassUtil
{
    /**
     * 判断是不是同样的构造函数
     * @return
     */
    public static boolean typeCheck(Class[] parameters, Object[] args)
    {
        if (NullCheckUtil.isNull(parameters,args))
        {
            return false;
        }

        if (parameters.length != args.length)
        {
            return false;
        }

        for (int i = 0; i < parameters.length; ++i)
        {
            if (!isAssign(parameters[i], args[i].getClass()))
            {
                return false;
            }
        }
        return true;
    }


    /**
     * 给属性设置值
     * @param o
     * @param fields
     * @param values
     */
    public static Object setFiled(Object o, Field[][] fields, List<String> values,String project)
    {

        Field[] assignFiled = getFiled(fields, values);

        if (assignFiled != null)
        {
            for (int index = 0; index < assignFiled.length; ++index)
            {
                Field f = assignFiled[index];
                String str = values.get(index);
                try
                {
                    f.setAccessible(true);
                    setFiledValue(o, f, str);
                }
                catch (IllegalAccessException e)
                {
                   LoggerManager.record(LoggerType.WARN, "can not set value : " + f.getName());
                }
                catch (SecurityException e)
                {
                    LoggerManager.record(LoggerType.WARN, "illegal access properties :" + f.getName());
                }
            }
        }
        return o;
    }


    /**
     * 获取跟field对应的值
     * @param field
     * @param string
     * @return
     */
    private static void setFiledValue(Object o, Field field, String string) throws IllegalAccessException
    {
        Class fieldCls = field.getClass();
        //如果是字符类型
        if (String.class.equals(fieldCls) || String.class.isAssignableFrom(fieldCls))
        {
            field.set(o, string);
        }

        //如果是字符类型
        if (Boolean.class.equals(fieldCls) || boolean.class.equals(fieldCls))
        {
            Boolean boolValue = null;
            if (string != null)
            {
                boolValue = string.equals("1") || string.equals("true");
                field.set(o, boolValue);
            }
        }

        if (fieldCls.isPrimitive())
        {
            setPrimitive(o, field, string);
        }
    }

    /**
     *
     * @param field
     * @param string
     */
    private static void setPrimitive(Object o, Field field,String string) throws IllegalAccessException
    {
        Class cls = field.getClass();
        //设置
        if (int.class.equals(cls) || Integer.class.equals(cls))
        {
            Matcher matcher = Pattern.compile("^\\d\\.\\-").matcher(string);
            if (matcher.find())
            {
                field.set(o,Integer.parseInt(string));
            }
        }

        if (double.class.equals(cls) || Double.class.equals(cls))
        {
            Matcher matcher = Pattern.compile("^\\d\\.\\-").matcher(string);
            if (matcher.find())
            {
                field.set(o,Double.parseDouble(string));
            }
        }

        if (float.class.equals(cls) || Float.class.equals(cls))
        {
            Matcher matcher = Pattern.compile("^\\d\\.\\-").matcher(string);
            if (matcher.find())
            {
                field.set(o,Float.parseFloat(string));
            }
        }

        //如果为日期
        if (Date.class.equals(cls))
        {
            Matcher matcher = Pattern.compile("^\\[1-9]{4}\\-([0][1-9])|([1][0-2])\\-([0-2][0-9]|[3][0-1])").matcher(string);
            if (matcher.find())
            {
                field.set(o,new Date(string));
            }
        }

    }



    /**
     * 获取给哪个Filed组赋值
     * @param fields
     * @param values
     * @return
     */
    private static Field[] getFiled(Field[][] fields, List<String> values)
    {

        if (fields.length == 1)
        {
            return fields[0];
        }

        for (Field[] field : fields)
        {
            if (values.size() == field.length)
            {
                return field;
            }
        }
        return null;
    }


    /**
     * 判断是否能够赋值
     * @param src
     * @param snk
     * @return
     */
    private static boolean isAssign(Class src, Class snk)
    {
        if (src.isAssignableFrom(snk))
        {
            return true;
        }

        try
        {
            if (src.isPrimitive() && src.getField("TYPE").get(null) == snk)
            {
                return true;
            }

            if (snk.isPrimitive() && snk.getField("TYPE").get(null) == src)
            {
                return true;
            }
        }
        catch (IllegalAccessException | NoSuchFieldException e)
        {
            LoggerManager.record(LoggerType.WARN, "Type is error");
        }
        return false;
    }




}
