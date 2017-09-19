package yyh.munia.com.prgress.impl;

import yyh.munia.com.prgress.inf.IProgressOperator;
import yyh.munia.com.prgress.inf.IProgressWriter;
import yyh.munia.com.prgress.model.EnumTaskStatus;
import yyh.munia.com.prgress.model.ProgressConstant;
import yyh.munia.com.prgress.model.TaskProgress;
import yyh.munia.com.prgress.model.TaskStatusToken;
import yyh.munia.com.util.GsonUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oak on 2017/9/19.
 */
public class ProgressWriter implements IProgressWriter<TaskProgress>,ProgressConstant
{

    /**
     * taskKey
     */
    private String taskKey;

    /**
     * 读写器
     */
    private IProgressOperator operator;

    /**
     * 需要存储到redis中的Type转化类
     */
    private Type type = new TaskStatusToken().getType();

    /**
     * 构造函数
     * @param iProgressOperator
     */
    public ProgressWriter(IProgressOperator iProgressOperator)
    {
        this.operator = iProgressOperator;
    }



    @Override
    public void log(EnumTaskStatus enumTaskStatus, int progress, String message, String... object)
    {
        int actualProgress = actrualProgress(progress);

        TaskProgress taskProgress = new TaskProgress();
        List<String> messages = new ArrayList<>();
        taskProgress.setEnumTaskStatus(enumTaskStatus);
        messages.add(message);
        taskProgress.setMessages(messages);
        taskProgress.setProgress(actualProgress);
        this.operator.write(this.taskKey, GsonUtil.gsonFrom(taskProgress));
    }

    @Override
    public void log(int progress, String message, String... object)
    {
        int actualProgress = actrualProgress(progress);
        TaskProgress taskProgress = new TaskProgress();
        List<String> messages = new ArrayList<>();
        messages.add(message);
        taskProgress.setMessages(messages);
        taskProgress.setProgress(actualProgress);

        this.operator.write(this.taskKey, GsonUtil.gsonFrom(taskProgress));
    }

    @Override
    public TaskProgress clear()
    {
        String result = this.operator.clear(this.taskKey);
        return GsonUtil.toObject(result, type);
    }


    @Override
    public void setKey(String key)
    {
        this.taskKey = key;
    }

    /**
     * 对设置的进度进行过滤
     * @param progress
     * @return
     */
    private int actrualProgress(int progress)
    {
        return progress < MIN_PERCENT ? MIN_PERCENT : progress > MAX_PERCENT ? MAX_PERCENT : progress;
    }

}
