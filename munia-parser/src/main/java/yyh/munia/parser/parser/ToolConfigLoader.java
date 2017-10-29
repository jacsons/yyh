package yyh.munia.parser.parser;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;
import yyh.munia.com.fileParser.ITravel;
import yyh.munia.com.fileParser.model.EFileType;
import yyh.munia.com.fileParser.model.PathHolder;
import yyh.munia.com.prgress.model.EnumTaskStatus;
import yyh.munia.com.util.*;
import yyh.munia.parser.parser.model.ObjectWrapp;
import yyh.munia.parser.parser.model.Wrap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 解析toolconfig的工具
 * Created by oak on 2017/10/14.
 */
public class ToolConfigLoader
{

    /**
     * 存储toolConfig中key vale；
     */
    private static Map<String, List<ConfigValueGet>> prop = new HashMap<>();

    /**
     * 对象锁
     */
    private static Object objectLock = new Object();


    /**
     * 路径存储器
     */
    private static PathHolder holder = new PathHolder();

    /**
     * 初始化路径
     */
    static
    {
        holder.addPath("classpath:toolconfig.xml");
    }


    /**
     * 获取资源
     *
     * @return
     */
    public static synchronized Map<String, List<ConfigValueGet>> getProp()
    {
        if (prop == null)
        {
            synchronized (objectLock)
            {
                if (prop == null)
                {
                    LazyLoad();
                }
            }
        }
        return prop;
    }


    /**
     * 延迟加栽
     */
    private static void LazyLoad()
    {
        prop = new ConcurrentHashMap<>();

        while (holder.hasNext())
        {
            String file = holder.next();
            FileTaskMagr fileTaskMagr = new FileTaskMagr(new ToolConfigTravel(), EFileType.XML_TYPE, file);
            if (fileTaskMagr.main() != EnumTaskStatus.SUCCESS)
            {
                LoggerManager.record(LoggerType.ERROR, "parser file error, file is :" + file);
            }
        }
    }

    /**
     * 获取解析中的值
     *
     * @param key
     * @return
     */
    static List<ConfigValueGet> getProp(String key)
    {
        if (!prop.containsKey(key))
        {
            prop.put(key, new ArrayList<>());
        }

        return prop.get(key);
    }


    /**
     * 解析回掉的类
     */
    private static class ToolConfigTravel implements ITravel
    {
        /**
         * DECLARE
         */
        private static final String IMPORT = "import";

        /**
         * travels
         */
        private static final String WRAPPERS = "travels";

        /**
         * d
         */
        private static final String UNIQUE = "unique";


        /**
         * 声明对象
         */
        private static final String WRAPPS = "wrappers";

        /**
         * @return
         */
        public boolean travelStart()
        {
            return true;
        }

        /**
         * @param objects
         * @return
         */
        public boolean travel(Object... objects)
        {
            if (NullCheckUtil.isNull(objects))
            {
                LoggerManager.record(LoggerType.INFO, "parser toolconfig.xml, null line : ");
            }
            Element element = (Element) objects[0];
            LoggerManager.record(LoggerType.INFO, " toolconfig.xml node name is " + element.getName());

            //toolconfig配置。
            toolConfigParser(element);
            return true;
        }

        /**
         *
         */
        public void travelEnd()
        {


        }

        /**
         * toolconfig解析函数
         *
         * @param element
         */
        @SuppressWarnings("uncheck")
        private void toolConfigParser(Element element)
        {
            String name = element.getName();

            if (IMPORT.equals(name))
            {

                String src = XmlElementUtil.attributeValue(element, "src");
                if (StringUtils.isEmpty(src))
                {
                    holder.addPath(src);
                }
                return;
            }

            if (WRAPPERS.equals(name))
            {
                wrapper(element);
                return;
            }

            //读取config的信息
            List<ConfigValueGet> configValueGets = getProp(name);
            List<Element> elements = element.elements("prop");
            if (elements != null)
            {
                for (Element e : elements)
                {
                    String key = XmlElementUtil.attributeValue(e, "key");
                    List<String> values = XmlElementUtil.attributes(e, "key");

                    for (String value : values)
                    {
                        configValueGets.add(new ConfigValueGet(key, value));
                    }
                }
            }

        }

        /**
         * 设置wrap
         * @param element
         */
        private void wrapper(Element element)
        {
            if (element == null)
            {
                return;
            }

            List<Element> elements = element.elements(WRAPPS);
            if (elements != null)
            {

                for (Element e : elements)
                {
                    Wrap wrap = new Wrap();
                    XmlElementUtil.fieldSet(e, wrap);
                    List<Element> els = e.elements("field");
                    String[][] fileds = new String[els.size()][];
                    int i = 0;
                    for (Element ele : els)
                    {
                        String[] values = ele.getTextTrim().split("\\s,\\s");
                        fileds[i++] = values;
                    }
                    wrap.setFiled(fileds);

                    String key = XmlElementUtil.attributeValue(e, "key");
                    String className = XmlElementUtil.attributeValue(e, "className");


                    if (StringUtils.isEmpty(key) || StringUtils.isEmpty(className))
                    {
                        LoggerManager.record(LoggerType.WARN,"key or className is null : " + key + " " + className);
                        continue;
                    }
                    ObjectWrapp.put(key, className);
                }
            }
        }

    }

}
