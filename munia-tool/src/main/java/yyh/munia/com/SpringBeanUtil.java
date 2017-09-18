package yyh.munia.com;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;


/**
 * Created by oak on 2017/8/26.
 */
public class SpringBeanUtil
{

    private static ApplicationContext ctx;


    static
    {
        init();
    }


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
     * @param beanId
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T getBean(String beanId, Class<T> cls)
    {
        return ctx.getBean(beanId,cls);
    }


    /**
     * 初始化Spring,启动时调用
     */
    public static void init()
    {
        LoggerManager.record(LoggerType.INFO,System.getProperty("usr.dir"));
        String[] url = new String[]{"classpath:spring_progress.xml"};
        ctx = new FileSystemXmlApplicationContext(url);

    }


    /**
     * 获取上下文
     * @return
     */
    public static ApplicationContext getCtx()
    {
        return ctx;
    }

    /**
     * 设置上下文
     * @param ctx
     */
    public static void setCtx(ApplicationContext ctx)
    {
        SpringBeanUtil.ctx = ctx;
    }
}
