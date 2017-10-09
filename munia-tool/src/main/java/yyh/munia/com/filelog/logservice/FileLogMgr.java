package yyh.munia.com.filelog.logservice;

import yyh.munia.com.filelog.inf.IMultLogTools;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * 日志管理类
 * Created by oak on 2017/10/7.
 */
public class FileLogMgr implements IMultLogTools
{


    private static final FileLogMgr fileLogMgr = new FileLogMgr();

    /**
     * 用于日志的输出
     */
    private Map<String,Object> stringObjectMap = new HashMap<>();

    /**
     * 最大同时输出的量
     */
    private static final int MAT_TOOL_NUMBER = 5;

    /**
     * 当前输出的数量
     */
    private int currentFlushNumber = 0;


    /**
     * 日志记录器
     */
    private LogBuffer logBuffer = new LogBuffer();


    @Override
    public void recordMessage(String taskId, String modelId, String... message)
    {
        logBuffer.recordMessage(taskId, modelId, message);
    }

    @Override
    public void flush(String taskId)
    {
        logBuffer.flush(taskId);
    }

    @Override
    public String compressFile(String taskId)
    {
        return logBuffer.compressFile(taskId);
    }

    @Override
    public Set<String> listAllTask()
    {
        return logBuffer.listAllTask();
    }

    public static FileLogMgr getInsttance()
    {
        return fileLogMgr;
    }
}
