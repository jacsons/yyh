package yyh.munia.com.filelog.logservice;

import yyh.munia.com.filelog.inf.IMultLogTools;
import yyh.munia.com.filelog.inf.IRecordTools;
import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;

import java.util.*;

/**
 * 日志的存储器，存储日志信息在内存中, 后面需要修改支持并发
 * Created by oak on 2017/10/7.
 */
public class LogBuffer implements IMultLogTools
{

    /**
     * 日志记录器
     */
    private IRecordTools iRecordTools = new TxtRecordTools();


    /**
     * 记录日志信息的缓存
     */
    private Map<String,List<String>> messageMap = new HashMap<>();



    @Override
    public void recordMessage(String taskId, String modelId, String... message)
    {

        String key = messageMapkey(taskId, modelId);
        List<String> messages = messageMap.get(key);

        if (messages == null)
        {
            messages = new ArrayList<>();
            messageMap.put(key, messages);
        }

        for (String msg : message)
        {
            messages.add(msg);
        }
    }

    /**
     * 刷新所有的日志信息
     * @param taskId
     */
    @Override
    public void flush(String taskId)
    {
        Set<String> keys = messageMap.keySet();
        List<String> flushkeys = new ArrayList<>();
        for (String key : keys)
        {
            if (key.startsWith(taskId + "@@"))
            {
                flushkeys.add(key);
            }
        }

        if (flushkeys.isEmpty())
        {
            LoggerManager.record(LoggerType.ERROR, "don't exist messages , taskId is" + taskId);
        }

        for (String flushkey : flushkeys)
        {

            iRecordTools.writeMessage(taskId, flushkey.substring(flushkey.indexOf(taskId + "@@")), messageMap.get(flushkey));
        }
    }

    /**
     * 压缩通一个任务下的文件信息
     * @param taskId
     * @return
     */
    @Override
    public String compressFile(String taskId)
    {
        return iRecordTools.compressFiles(taskId);
    }

    /**
     * 列初所有的任务id
     * @return
     */
    @Override
    public Set<String> listAllTask()
    {
        Set<String>  results = new HashSet<>();
        Set<String> keys = messageMap.keySet();
        for (String key : keys)
        {
            int index = key.indexOf("@@");
            if (index != -1)
            {
                results.add(key.substring(0, index));
            }
        }

        return results;
    }

    /**
     * 获取meesagemap的Key
     * @param taskId
     * @param modelId
     * @return
     */
    private String messageMapkey(String taskId, String modelId)
    {
        return taskId + "@@" +modelId;
    }

}
