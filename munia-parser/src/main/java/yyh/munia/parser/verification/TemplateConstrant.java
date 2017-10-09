package yyh.munia.parser.verification;

import yyh.munia.parser.verification.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解析初模版的限制条件
 * Created by oak on 2017/10/8.
 */
public class TemplateConstrant
{

    /**
     * 列名称
     */
    private Map<String,List<String>> columnEnNamesMap = new HashMap<>();

    /**
     * 列名称
     */
    private Map<String,List<String>> columnZhNamesMap = new HashMap<>();

    /**
     * 列的限制条件，获取列的列的限制条件，（数据类型、值范围等）
     */
    private Map<String,ConstrantModel> constrantMap = new HashMap<>();


    /**
     * 列的限制条件
     */
    private Map<String,String> typeMap = new HashMap<>();


    /**
     * 列的依赖关系模型
     */
    private Map<String,DependentModel> dependentModelMap = new HashMap<>();


    /**
     * 是否已经初始化
     */
    private boolean isInit = false;


    /**
     * 初始化各种依赖 关系
     */
    public boolean init()
    {

        VerificationParser verificationParser = VerificationParser.getInstance();
        Map<String, TemplateModel> templateModelMap = verificationParser.getTemplateModelMap();

        //获取模版的列名
        for (Map.Entry<String, TemplateModel> entry: templateModelMap.entrySet())
        {
            TemplateModel templateModel = entry.getValue();
            List<ColumnsModel> columnsModelList = templateModel.getColumnsModels();
            //英文名称
            List<String> columnEnNames = new ArrayList<>();
            //中文名称
            List<String> columnZhNames = new ArrayList<>();

            for (ColumnsModel columnsModel : columnsModelList)
            {
                List<ColumnModel> columnModelList = columnsModel.getColumnModels();
                for (ColumnModel columnModel : columnModelList)
                {
                    String nameEn = columnModel.getNameEn();
                    String nameZh = columnModel.getNameZh();

                    columnEnNames.add(nameEn);
                    columnZhNames.add(nameZh);

                    String uniqueKey = entry.getKey() + "@@" + templateModel.getNameZh() + "@@" + columnsModel.getId() +"@@" +columnModel.getNameZh();
                    constrantMap.put(uniqueKey, columnModel.getConstrantModel());
                    typeMap.put(uniqueKey, columnModel.getType());
                    dependentModelMap.put(uniqueKey, columnModel.getDependentModel());
                }
            }

            columnEnNamesMap.put(entry.getKey() + "@@" + templateModel.getNameEn(), columnEnNames);
            columnZhNamesMap.put(entry.getKey() + "@@" + templateModel.getNameZh(), columnZhNames);

        }

        return true;
    }

}
