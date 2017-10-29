package yyh.munia.com.util;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Element;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * XmlElement 处理类
 * Created by oak on 2017/10/16.
 */
public class XmlElementUtil
{

    /**
     * 获取属性值
     *
     * @param element
     * @param type
     * @return
     */
    public static String attributeValue(Element element, String type)
    {
        if (StringUtils.isEmpty(type) || element == null)
        {
            return null;
        }

        String value = element.attributeValue(type);
        if (value != null)
        {
            return value;
        }

        Element ele = element.element(type);

        if (ele != null)
        {
            return ele.getTextTrim();
        }

        return "";
    }

    /**
     * 获取某一个key下的连续属性
     * @param element
     * @param type
     * @return
     */
    public static List<String> attributes(Element element, String type)
    {
        if (StringUtils.isEmpty(type) || element == null)
        {
            return new ArrayList<>(0);
        }

        List<String> result = new ArrayList<>();

        Attribute attribute = element.attribute("type");
        if (attribute != null)
        {
            result.add(attribute.getText().trim());
        }

        List<Element> elements = element.elements("value");

        if (elements == null)
        {
            for (Element e : elements)
            {
                result.add(e.getTextTrim());
            }
        }
        return result;
    }

    /**
     * 设置wrap的属性
     *
     * @param element
     * @param wrap
     */
    public static void fieldSet(Element element, Object wrap)
    {
        if (wrap == null)
        {
            return;
        }

        Class cls = wrap.getClass();

        //获取类中的属性
        Field[] fields = cls.getFields();
        try
        {
            for (Field field : fields)
            {
                String value = attributeValue(element, field.getName());

                if (StringUtils.isEmpty(value) && String.class.equals(cls))
                {
                    field.setAccessible(true);
                    field.set(wrap, value);
                }
            }
        }
        catch (IllegalAccessException e)
        {
            LoggerManager.record(LoggerType.ERROR, "fieldSet error", e);
        }
    }

}
