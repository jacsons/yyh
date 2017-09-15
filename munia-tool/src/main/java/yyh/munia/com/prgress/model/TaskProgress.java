package yyh.munia.com.prgress.model;

import java.util.List;

/**
 * Created by oak on 2017/9/15.
 */
public class TaskProgress
{
    /**
     *
     * 运行状态
     *
     */
    private EnumTaskStatus enumTaskStatus;

    /**
     *
     * message信息
     *
     */
    private List<String> messages;


    /**
     *
     * 进度
     *
     */
    private String progress;


    public EnumTaskStatus getEnumTaskStatus()
    {
        return enumTaskStatus;
    }

    public void setEnumTaskStatus(EnumTaskStatus enumTaskStatus)
    {
        this.enumTaskStatus = enumTaskStatus;
    }

    public List<String> getMessages()
    {
        return messages;
    }

    public void setMessages(List<String> messages)
    {
        this.messages = messages;
    }

    public String getProgress()
    {
        return progress;
    }

    public void setProgress(String progress)
    {
        this.progress = progress;
    }

}
