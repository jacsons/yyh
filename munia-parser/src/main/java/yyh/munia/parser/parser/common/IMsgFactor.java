package yyh.munia.parser.parser.common;

import yyh.munia.parser.parser.ConfigValueGet;
import yyh.munia.parser.parser.ToolConfigLoader;
import yyh.munia.parser.parser.inf.IHanlder;
import yyh.munia.parser.parser.model.LazyHashMap;
import yyh.munia.parser.parser.model.ListLazyHashMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 初始化的实例
 * Created by oak on 2017/10/14.
 */
public class IMsgFactor
{

    /**
     * 处理器
     */
    private static final String HANDLER = "handler";

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

    /**
     * 基础处理器类
     */
    private LazyHashMap<IHanlder> delareMap;

    /**
     *处理器类
     */
    private ListLazyHashMap<IHanlder> handlerMap;

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
    private void init(IMsgFactor iMsgFactor, Map<String,Object> def)
    {
        Map<String, List<ConfigValueGet>> configValueMap = ToolConfigLoader.getProp();
        List<ConfigValueGet> configValueGets = configValueMap.get(DECLARE);



        //获取DECLARE处理器
        if (configValueGets != null)
        {

            for (ConfigValueGet configValueGet : configValueGets)
            {
                def.put(configValueGet.getKey(), configValueGet.getValue(def, "0"));
            }
        }

        //获取所有的handlers
        configValueGets = configValueMap.get(HANDLER);
        if (configValueGets != null)
        {
            iMsgFactor.handlerMap = new ListLazyHashMap<IHanlder>(def,handler -> handler.afterInit(),handler -> handler.afterInit());
            for (ConfigValueGet configValueGet : configValueGets)
            {
                iMsgFactor.handlerMap.put(configValueGet);
            }
        }


        //取出wrapper
        configValueGets = configValueMap.get(Wrapper);
        if (configValueGets != null)
        {
            for (ConfigValueGet configValueGet : configValueGets)
            {
//                ObjectWrapp.put(configValueGet.getKey(),configValueGet.ge);
            }
        }
    }







}
