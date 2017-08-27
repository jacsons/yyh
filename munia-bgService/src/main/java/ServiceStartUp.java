import yyh.munia.bgService.model.Constant;
import yyh.munia.com.SpringUtil;
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

        SpringUtil.springInit();

        LegoTaskSchedule.start();


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
