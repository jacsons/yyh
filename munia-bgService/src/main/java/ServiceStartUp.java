import yyh.dbservice.com.db.ProjectDBMgr;
import yyh.dbservice.com.db.error.DBException;
import yyh.dbservice.com.db.model.EvaCache;
import yyh.munia.bgService.db.model.Constant;
import yyh.munia.com.SpringBeanUtil;
import yyh.munia.com.lagoTask.LegoTaskSchedule;


/**
 * 启动服务
 * Created by oak on 2017/8/4.
 */
public class ServiceStartUp
{

    public static void main(String[] args)
    {

        //系统
        System.setProperty("SYSTEM_RUNNING_DIR", Constant.SYSTEM_RUNNING_DIR);

        SpringBeanUtil.getCtx();

        LegoTaskSchedule.start();


        EvaCache.setProjectId("211111111111");
        ProjectDBMgr mgr = SpringBeanUtil.getBean("projectDBMgr",ProjectDBMgr.class);
        try
        {

            mgr.createProject("211111111111");
        } catch (DBException e)
        {
            e.printStackTrace();
        }
    }


    /**
     *
     *
     *
     *
     * 配置LogXml
     */
    private void setLog4jXml()
    {

    }

}
