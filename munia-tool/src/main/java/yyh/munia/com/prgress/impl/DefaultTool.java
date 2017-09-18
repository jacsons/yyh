package yyh.munia.com.prgress.impl;

import yyh.munia.com.prgress.inf.IProgressReader;
import yyh.munia.com.prgress.inf.IProgressWriter;
import yyh.munia.com.prgress.inf.IProgressOperator;
import yyh.munia.com.prgress.model.EnumTaskStatus;
import yyh.munia.com.prgress.model.ProgressConstant;
import yyh.munia.com.prgress.model.TaskProgress;
import yyh.munia.com.prgress.model.TaskStatusToken;
import yyh.munia.com.util.GsonUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oak on 2017/9/17.
 */
public class DefaultTool implements IProgressWriter<TaskProgress>, IProgressReader<TaskProgress>, ProgressConstant
{

    /**
     * 需要存储到redis中的Type转化类
     */
    private Type type = new TaskStatusToken().getType();

    /**
     * 存储在redis中的模块名称
     */
    private String taskKey;


    /**
     * 存储的接口，可以是文本，数据库，redis等，通过spring来配置。
     */
    private IProgressOperator operator;


    /**
     * 默认构造函数
     */
    public DefaultTool()
    {

    }

    /**
     * 给这个类复制读写器
     * @param operator
     */
    public DefaultTool(IProgressOperator operator)
    {
        super();
        this.operator = operator;

    }


    @Override
    public TaskProgress reapProgress()
    {

        String result = this.operator.read(this.taskKey);
        if(result == null)
        {
            return build();
        }
        return GsonUtil.toObject(result, type);
    }

    @Override
    public void log(EnumTaskStatus enumTaskStatus, int progress, String message, String... object)
    {
        int actualProgress = actrualProgress(progress);

        TaskProgress taskProgress = new TaskProgress();
        List<String> messages = new ArrayList<>();
        messages.add(message);
        taskProgress.setMessages(messages);
        taskProgress.setEnumTaskStatus(enumTaskStatus);
        taskProgress.setProgress(actualProgress);

        this.operator.write(this.taskKey,GsonUtil.gsonFrom(taskProgress));
    }

    /**
     * 这个认为是在增加日志信息
     * @param progress
     *      进度值
     * @param message
     * @param object
     */
    @Override
    public void log(int progress, String message, String... object)
    {
        int actualProgress = actrualProgress(progress);

        TaskProgress taskProgress = reapProgress();
        taskProgress.getMessages().add(message);
        taskProgress.setProgress(actualProgress);

        this.operator.write(this.taskKey, GsonUtil.gsonFrom(taskProgress));
    }

    @Override
    public TaskProgress clear()
    {
        TaskProgress taskProgress = reapProgress();
        this.operator.clear(this.taskKey);
        return taskProgress;
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

    /**
     * 构造一个默认的TaskProgress
     * @return
     */
    private TaskProgress build()
    {
        TaskProgress taskProgress = new TaskProgress();
        taskProgress.setEnumTaskStatus(EnumTaskStatus.NOT_EXIST);
        taskProgress.setProgress(0);
        taskProgress.setMessages(new ArrayList<>(0));
        return taskProgress;
    }

}
