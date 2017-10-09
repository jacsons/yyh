package yyh.munia.com.filelog;

import yyh.munia.com.filelog.logservice.FileLogMgr;

import java.util.Set;

/**
 * 文件日志输出流，（多文件并行输出，支撑整体性质的打包）
 * Created by oak on 2017/10/4.
 */
public class FileLogUtil
{

    //日志输出的功能
    /**
     *  功能需求：1、能够根据特定的key值进行单个文件的txt输出。
     *          2、同一个项目下的文件能够进行打包整体性的输出
     *
     *  实现想法：注意点： 文件同时输出数量需要进行控制、文件输出不要频发的输出，需要存储到一定的量再输出
     *          1、传入参数两个：任务id，单独文件的id，需要输入的信息。
     *          2、定时与定量刷新至文件中的功能。
     *          3、能够支持按照任务id进行压缩功能。
     */



    private static FileLogMgr fileLogMgr = FileLogMgr.getInsttance();
    /**
     * 记录日志
     * @param taskId
     * @param modelId
     * @param message
     */
    public static synchronized void recordMessage(String taskId, String modelId, String...message)
    {
        //最多允许3个项目同时进行输出，超过一定量或者时间后自动刷新，对于后续排队的只允许存储，不允许写入。
        fileLogMgr.recordMessage(taskId, modelId, message);
    }

    /**
     * 刷新日志
     * @param taskId
     */
    public static synchronized void flush(String taskId)
    {
        fileLogMgr.flush(taskId);
    }

    /**
     * 压缩文件
     * @param taskId
     * @return
     */
    public static synchronized String compressFile(String taskId)
    {

        return fileLogMgr.compressFile(taskId);
    }

    /**
     * 返回所有的任务id
     * @return
     */
    public static synchronized Set<String> listAllTask()
    {
       return fileLogMgr.listAllTask();
    }
}
