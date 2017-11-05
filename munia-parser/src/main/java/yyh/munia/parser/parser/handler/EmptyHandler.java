package yyh.munia.parser.parser.handler;

import org.apache.commons.lang3.StringUtils;
import yyh.munia.parser.parser.inf.IHanlder;
import yyh.munia.parser.parser.model.EHandlerMsg;
import yyh.munia.parser.parser.model.MessageModel;

import java.util.List;

/**
 * 检测是否为空的处理器
 * Created by oak on 2017/10/30.
 */
public class EmptyHandler implements IHanlder
{
    /**
     * 处理类
     * @param msg
     * @return
     */
    @Override
    public boolean handler(MessageModel msg)
    {

        List<String> msgs = (List<String>) msg.getDatas();
        for (String data : msgs)
        {
            if (StringUtils.isEmpty(data))
            {
                msg.seteHandlerMsg(EHandlerMsg.FAILED);
                return false;
            }
        }
        msg.seteHandlerMsg(EHandlerMsg.NEW);
        return true;
    }
}
