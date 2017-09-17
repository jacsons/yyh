package yyh.munia.com.prgress.inf;

import yyh.munia.com.prgress.model.TaskProgress;

/**
 * 读操作接口
 * Created by oak on 2017/9/14.
 */
public interface IProgressReader extends IProgressKey
{


    /**
     * 获取进度消息
     * @param
     */
    TaskProgress reapProgress();




}
