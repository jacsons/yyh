package yyh.munia.com.filelog.inf;

import java.util.Set;

/**
 * Created by oak on 2017/10/7.
 */
public interface IMultLogTools
{

    /**
     *
     * @param taskId
     * @param modelId
     * @param message
     */
    void recordMessage(String taskId, String modelId, String...message);

    /**
     * 刷新日志
     * @param taskId
     */
    void flush(String taskId);


    /**
     * 压缩文件
     * @param taskId
     * @return
     */
    String compressFile(String taskId);


    /**
     * 返回所有的任务id
     * @return
     */
    Set<String> listAllTask();

}
