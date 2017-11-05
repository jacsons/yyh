package yyh.munia.parser.parser.inf;

import yyh.munia.parser.parser.model.MessageModel;

/**
 * 解析处理接口类
 * Created by oak on 2017/10/27.
 */
public interface IHanlder
{


    boolean handler(MessageModel msg);

    /**
     * 初始化init
     * @return
     */
    default boolean afterInit()
    {
        return true;
    }


    default boolean beforeInit()
    {

        return true;
    }
}
