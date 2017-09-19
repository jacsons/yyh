package yyh.munia.com.prgress.impl;

import yyh.munia.com.prgress.inf.IProgressOperator;
import yyh.munia.com.prgress.inf.IProgressReader;
import yyh.munia.com.prgress.model.EnumTaskStatus;
import yyh.munia.com.prgress.model.TaskProgress;
import yyh.munia.com.prgress.model.TaskStatusToken;
import yyh.munia.com.util.GsonUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by oak on 2017/9/19.
 */
public class ProgressReader implements IProgressReader<TaskProgress>
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

    public ProgressReader(IProgressOperator operator)
    {
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
    public void setKey(String key)
    {
        this.taskKey = key;
    }

    /**
     * 构造一个默认的TaskProgress
     * @return
     */
    private TaskProgress build()
    {
        TaskProgress taskProgress = new TaskProgress();
        taskProgress.setEnumTaskStatus(EnumTaskStatus.NOT_EXIST);
        taskProgress.setMessages(new ArrayList<>(0));
        taskProgress.setProgress(0);
        return taskProgress;
    }
}
