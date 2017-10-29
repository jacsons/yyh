package yyh.munia.parser.parser.common;

import yyh.munia.parser.parser.ConfigValueGet;
import yyh.munia.parser.parser.ToolConfigLoader;
import yyh.munia.parser.parser.inf.IAfterInit;
import yyh.munia.parser.parser.inf.IBeforeInit;
import yyh.munia.parser.parser.inf.IHanlder;
import yyh.munia.parser.parser.model.LazyHashMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by oak on 2017/10/14.
 */
public class IMsgFactor
{

    /**
     * 处理器
     */
    private static final String Handler = "handler";

    /**
     * wrapers
     */
    private static final String Wrapper = "wrappers";

    /**
     * declare
     */
    private static final String DECLARE = "declare";


    /**
     * 实例
     */
    private static IMsgFactor instance = new IMsgFactor();


    /**
     * 初始化的类
     */
    private Map<String,Object> def = new HashMap<>();


    private Map<String,Object> delareMap;

    /**
     * 获取实例
     * @return
     */
    public static IMsgFactor getInstance()
    {
        return instance;
    }


    /**
     * 出事化
     */
    private void init(IMsgFactor iMsgFactor)
    {
        Map<String, List<ConfigValueGet>> configValueMap = ToolConfigLoader.getProp();
        List<ConfigValueGet> configValueGets = configValueMap.get(DECLARE);



        if (configValueGets != null)
        {

            iMsgFactor.delareMap = new LazyHashMap<>(new IBeforeInit<IHanlder>()
            {
                @Override
                public boolean beforeInit(IHanlder handler)
                {
                    return handler.afterInit();
                }
            },new IAfterInit<IHanlder>(){

                @Override
                public boolean afterInit(IHanlder handler)
                {
                    return handler.afterInit();
                }
            });

            for (ConfigValueGet configValueGet : configValueGets)
            {


                iMsgFactor.delareMap.put()


            }
        }









    }







}
