package legoTask;

import org.junit.Test;
import yyh.munia.com.lagoTask.ILegoTask;
import yyh.munia.com.lagoTask.LegoTaskMgr;
import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;

/**
 *
 *
 * Created by oak on 2017/8/1.
 */
public class TestLegoTask
{

    @Test
    public void TestLego()
    {
        ILegoTask task = new ILegoTask()
        {
            @Override
            public Object main()
            {
                System.out.println("hello world");

                return 0;
            }

            @Override
            public Object handleException(RuntimeException e)
            {
                LoggerManager.record(LoggerType.INFO,"start process exception" + e);
                return 0;
            }
        };


        ILegoTask task1 = new ILegoTask()
        {
            @Override
            public Object main()
            {
                System.out.println("hello life");

                return 0;
            }

            @Override
            public Object handleException(RuntimeException e)
            {
                LoggerManager.record(LoggerType.INFO,"start process exception" + e);
                return 0;
            }
        };

        LegoTaskMgr.getInstance().submit(task);
        LegoTaskMgr.getInstance().submit(task1);

        try
        {
            Thread.sleep(30000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }



}
