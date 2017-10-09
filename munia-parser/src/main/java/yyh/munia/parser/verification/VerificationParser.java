package yyh.munia.parser.verification;

import org.dom4j.Element;
import yyh.munia.com.fileParser.ITravel;
import yyh.munia.com.fileParser.model.EFileType;
import yyh.munia.com.fileParser.model.PathHolder;
import yyh.munia.com.lagoTask.LegoTaskMgr;
import yyh.munia.com.util.FileTaskMagr;
import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;
import yyh.munia.com.util.NullCheckUtil;
import yyh.munia.parser.verification.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 验证模块解析
 * Created by oak on 2017/10/3.
 */
public class VerificationParser
{

    private static PathHolder pathHolder = new PathHolder();

    /**
     * 限制标志
     */
    private static final String XML_CONSTRANT = "constrant";

    /**
     * 依赖标志
     */
    private static final String XML_DEPENDENT = "dependent";

    /**
     * 本类的唯一实例
     */
    private static VerificationParser verificationParser = new VerificationParser();

    static
    {
        pathHolder.addPath("verification.xml");
    }

    /**
     * templateModelMap
     */
    private Map<String, TemplateModel> templateModelMap = new HashMap<>();

    /**
     * 是否进行初始化
     */
    private boolean isinit = false;

    private VerificationParser()
    {

    }

    /**
     * 获取该实例
     * @return
     */
    public static VerificationParser getInstance()
    {
        return verificationParser;
    }


    /**
     * 获取解析后的模版对象
     * @return
     */
    public synchronized Map<String, TemplateModel> getTemplateModelMap()
    {

        if(!isinit)
        {
            XmlParser();
            isinit = true;
        }
        return templateModelMap;
    }


    /**
     * Xml解析,启动的时候解析
     */
    private void XmlParser()
    {

        while (pathHolder.hasNext())
        {
            String file = pathHolder.next();
            FileTaskMagr fileTaskMagr = new FileTaskMagr(new verificationTravel(), EFileType.XML_TYPE, file);
            LegoTaskMgr.getInstance().submit(fileTaskMagr);
        }
    }

    /**
     * 解析xml中的元素
     * @param element
     */
    private void xmlElementParsre(Element element)
    {
        if (element == null || element.elements() == null)
        {
            return;
        }

        List<Element> templates = element.elements();

        for (Element templateNodes : templates)
        {
            if (templateNodes.getName().equals("templates"))
            {
                List<Element> elements = templateNodes.elements();

                List<TemplateModel> templateModels = new ArrayList<>();
                for (Element e : elements)
                {
                    templateModels.add(parserTemplate(e));
                }
            }
        }
    }


    /**
     * 组装templateMap
     * @param templateModels
     */
    private void assembleTemplateModelMap(List<TemplateModel> templateModels)
    {
        for (TemplateModel templateModel : templateModels)
        {
            templateModelMap.put(templateModel.getToken(), templateModel);
        }

    }

    /**
     * 组装templatemodel
     *
     * @param element
     * @return
     */
    private TemplateModel parserTemplate(Element element)
    {
        TemplateModel templateModel = new TemplateModel();

        String id = element.attributeValue("id");
        String nameZh = element.attributeValue("nameZh");
        String nameEn = element.attributeValue("nameEn");
        String token = element.attributeValue("token");

        templateModel.setId(id);
        templateModel.setNameEn(nameEn);
        templateModel.setNameZh(nameZh);
        templateModel.setToken(token);
        templateModel.setColumnsModels(parserColumns(element.elements()));

        return templateModel;
    }

    /**
     * 解析columns
     *
     * @param elements
     * @return
     */
    private List<ColumnsModel> parserColumns(List<Element> elements)
    {

        if (elements == null || elements.isEmpty())
        {
            return null;
        }


        List<ColumnsModel> columnsModels = new ArrayList<>();

        for (Element e : elements)
        {
            ColumnsModel columnsModel = new ColumnsModel();
            String id = e.attributeValue("id");
            columnsModel.setId(id);
            columnsModel.setColumnModels(parserColumn(e.elements()));

            columnsModels.add(columnsModel);

        }
        return columnsModels;
    }

    /**
     * 解析每一个单元格
     *
     * @param elements
     * @return
     */
    private List<ColumnModel> parserColumn(List<Element> elements)
    {
        List<ColumnModel> columnModelList = new ArrayList<>();

        for (Element element : elements)
        {
            ColumnModel columnModel = new ColumnModel();
            String id = element.attributeValue("id");
            String nameZh = element.attributeValue("nameZh");
            String nameEn = element.attributeValue("nameEn");
            String type = element.attributeValue("type");

            columnModel.setId(id);
            columnModel.setNameZh(nameZh);
            columnModel.setNameEn(nameEn);
            columnModel.setType(type);

            List<Element> elementList = element.elements();

            for (Element e : elementList)
            {
                columnModel.setConstrantModel(parserConstrant(e));
                columnModel.setDependentModel(parserDependent(e));
            }
            columnModelList.add(columnModel);
        }
        return columnModelList;
    }


    /**
     * 解析degpengdent
     * @param element
     * @return
     */
    private DependentModel parserDependent(Element element)
    {
        if (!XML_DEPENDENT.equals(element.getName()))
        {
            return null;
        }

        DependentModel dependentModel = new DependentModel();

        String templateId = element.attributeValue("templateId");
        String columns = element.attributeValue("columns");
        String column = element.attributeValue("column");

        dependentModel.setTemplateId(templateId);
        dependentModel.setColumns(columns);
        dependentModel.setColumn(column);

        return dependentModel;
    }

    /**
     * 解析依赖模型
     * @param element
     * @return
     */
    private ConstrantModel parserConstrant(Element element)
    {
        if (!XML_CONSTRANT.equals(element.getName()))
        {
            return null;
        }

        ConstrantModel constrantModel = new ConstrantModel();

        String min = element.attributeValue("min");
        String emptyable = element.attributeValue("emptyable");

        constrantModel.setMin(min);
        constrantModel.setEmptyable(emptyable);

        return constrantModel;
    }


    /**
     * 解析回掉的类
     */
    private class verificationTravel implements ITravel
    {
        /**
         *
         * @return
         */
        public boolean travelStart()
        {
            return true;
        }

        /**
         *
         * @param objects
         * @return
         */
        public boolean travel(Object... objects)
        {
            if (NullCheckUtil.isNull(objects))
            {
                LoggerManager.record(LoggerType.INFO, "parser xml, null line : ");
            }
            Element element = (Element) objects[0];
            LoggerManager.record(LoggerType.INFO, "node name is " + element.getName());
            xmlElementParsre(element);

            return true;
        }

        /**
         *
         */
        public void travelEnd()
        {


        }
    }
}
