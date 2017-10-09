package yyh.munia.com.filelog.inf;

import java.util.List;

/**
 * Created by oak on 2017/10/7.
 */
public interface IRecordTools
{
    void writeMessage(String taskKey, String modelName, List<String> messages);

    String compressFiles(String taskkey);
}
