package yyh.munia.com.prgress.inf;

import yyh.munia.com.prgress.model.EnumTaskStatus;
import yyh.munia.com.prgress.model.TaskProgress;

/**
 * 写操作接口
 * Created by oak on 2017/9/14.
 */
public interface IProgressWriter extends IProgressKey
{

    /**
     * 记录运行状态
     * @param enumTaskStatus
     * @param message
     */
    void log(EnumTaskStatus enumTaskStatus, int progress, String message, String...object);

    /**
     * 记录日志信息
     * @param progress
     *      进度值
     * @param message
     *      额外的消息
     */
    void log(int progress, String message, String...object);


    /**
     * 清除redis中的值
     * @return
     */
    TaskProgress clear();



}
