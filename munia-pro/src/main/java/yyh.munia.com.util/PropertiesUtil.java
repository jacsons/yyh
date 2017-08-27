package yyh.munia.com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * 资源管理类
 * Created by oak on 2017/7/27.
 */
public class PropertiesUtil
{
    /**
     * 全路径文件名称
     */
    private static String fullFileName = null;

    /**
     * config所在的文件
     */
    private static final String CONFIG_DIR = "config";

    /**
     * 资源文件位置
     */
    private static final String CONFIG_NAME = "config.properties";

    /**
     * 资源文件类
     */
    private Properties properties = new Properties();


    /**
     * 全路名称
     */
    static
    {
        fullFileName = System.getProperty("user.dir") + File.separator + CONFIG_DIR + File.separator + CONFIG_NAME;

    }

    /**
     * 单例模式
     */
    private PropertiesUtil()
    {
        try
        {
            properties.load(new FileInputStream(fullFileName));
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.toString());
        }
        catch (IOException e)
        {
            System.out.println(e.toString());
        }
    }

    /**
     * 获取资源文件，使用同步保护（还是有缺陷的）
     * @return
     */
    public synchronized static Properties getProperties()
    {
        return LazyPropertiesUtil.propertiesUtil.properties;

    }


    /**
     * 采用该类的方式才能保证不会重复初始化
     */
    private static class LazyPropertiesUtil
    {
        public static PropertiesUtil propertiesUtil = new PropertiesUtil();

    }
}
