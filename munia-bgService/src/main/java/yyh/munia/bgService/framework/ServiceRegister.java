package yyh.munia.bgService.framework;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;

/**
 * 注册服务的类
 * Created by oak on 2017/9/10.
 */
public class ServiceRegister implements BeanPostProcessor
{

    /**
     *
     * @param o
     * @param s
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException
    {

        LoggerManager.record(LoggerType.INFO,"ServiceRegister postProcessBeforeInitialization start");


        LoggerManager.record(LoggerType.INFO,"ServiceRegister postProcessBeforeInitialization end");
        return o;
    }

    /**
     *
     * @param o
     * @param s
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException
    {
        LoggerManager.record(LoggerType.INFO,"ServiceRegister postProcessAfterInitialization start");


        LoggerManager.record(LoggerType.INFO,"ServiceRegister postProcessAfterInitialization end");
        return o;
    }
}
