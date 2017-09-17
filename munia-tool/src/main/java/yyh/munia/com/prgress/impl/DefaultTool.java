package yyh.munia.com.prgress.impl;

import yyh.munia.com.prgress.inf.IProgressReader;
import yyh.munia.com.prgress.inf.IProgressWriter;
import yyh.munia.com.prgress.model.EnumTaskStatus;
import yyh.munia.com.prgress.model.TaskProgress;

/**
 * Created by oak on 2017/9/17.
 */
public class DefaultTool implements IProgressWriter,IProgressReader
{

    @Override
    public TaskProgress reapProgress()
    {



        return null;
    }

    @Override
    public void log(EnumTaskStatus enumTaskStatus, int progress, String message, String... object)
    {



    }

    @Override
    public void log(int progress, String message, String... object)
    {



    }

    @Override
    public void setKey(String key)
    {



    }
}
