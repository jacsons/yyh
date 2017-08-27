package yyh.munia.com;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Created by oak on 2017/8/26.
 */
public class SpringUtil
{

    private static ApplicationContext ctx;


    /**
     * 根据class获取bean对象
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> cls)
    {
        return ctx.getBean(cls);
    }

    /**
     * 根据serviceName 与class 获取bean对象
     * @param beanName
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T getBean(String beanName, Class<T> cls)
    {
        return ctx.getBean(beanName,cls);
    }


    /**
     * 初始化Spring,启动时调用
     */
    public static void springInit()
    {
        String[] url = new String[]{"classpath:spring_content.xml"};
        ctx = new ClassPathXmlApplicationContext(url);
    }


}
