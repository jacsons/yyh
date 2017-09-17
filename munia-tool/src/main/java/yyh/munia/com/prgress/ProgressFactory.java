package yyh.munia.com.prgress;

import yyh.munia.com.prgress.impl.DefaultTool;
import yyh.munia.com.prgress.inf.IProgressReader;
import yyh.munia.com.prgress.inf.IProgressWriter;
import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;

/**
 * 对外接口，获取读与写的操作器，进行后续的读写操作
 * Created by oak on 2017/9/14.
 */
public class ProgressFactory
{
    /**
     * 默认的读操作器
     */
    private static final Class<? extends IProgressReader> iProgressReader = null;

    /**
     * 默认的写操作器
     */
    private static final Class<? extends IProgressWriter> iProgressWriter = null;


    /**
     * 写数据构造的参数
     */
    private Object[] writeArges;


    /**
     * 读构造函数的参数
     */
    private Object[] readArges;



    /**
     * 构建写进度器
     * @param key
     * @return
     */

    public IProgressWriter buildWriter(String key)
    {
        Class progressClass = iProgressWriter == null ? DefaultTool.class : iProgressReader;

        try
        {
            return writeArges.length == 0 ? (IProgressWriter)progressClass.newInstance() : (IProgressWriter)newInstance(progressClass, writeArges);
        }
        catch (InstantiationException | IllegalAccessException e)
        {
            return  (IProgressWriter)defalutProgress();
        }

    }

    /**
     * 获取度期器件
     * @param key
     * @return
     */
    public IProgressReader builderReader(String key)
    {
        Class progressClass = iProgressReader == null ? DefaultTool.class : iProgressReader;

        try
        {
            return readArges.length == 0 ? (IProgressReader)progressClass.newInstance() : (IProgressReader)newInstance(progressClass, readArges);
        }
        catch (InstantiationException | IllegalAccessException e)
        {
            return  (IProgressReader)defalutProgress();
        }

    }


    /**
     * 生成对象
     * @param args
     * @return
     */
    private Object newInstance(Class cls, Object[] args)
    {

        Constructor<?>[] constructors = cls.getConstructors();

        for (Constructor constructor : constructors)
        {
            Parameter[] parameters = constructor.getParameters();
            if(parameters.length == args.length)
            {

                try
                {
                    return constructor.newInstance(args);
                }
                catch (InstantiationException | IllegalAccessException | InvocationTargetException e)
                {
                    LoggerManager.record(LoggerType.ERROR,"newInstance failed", e);
                }
            }
        }
        return null;
    }


    /**
     * 默认的读写器
     * @return
     */
    private Object defalutProgress()
    {
        try
        {
            return  DefaultTool.class.newInstance();
        }
        catch (InstantiationException | IllegalAccessException e)
        {
            LoggerManager.record(LoggerType.ERROR,"defalutProgress init failed");
        }
        return new DefaultTool();
    }

}
