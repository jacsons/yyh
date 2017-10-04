package yyh.munia.parser.verification.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 模版信息块
 * Created by oak on 2017/10/3.
 */
public class TemplateModel
{
    private String id;
    private String nameZh;
    private String nameEn;
    private String token;

    private List<ColumnsModel> columnsModels = new ArrayList<ColumnsModel>();

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getNameZh()
    {
        return nameZh;
    }

    public void setNameZh(String nameZh)
    {
        this.nameZh = nameZh;
    }

    public String getNameEn()
    {
        return nameEn;
    }

    public void setNameEn(String nameEn)
    {
        this.nameEn = nameEn;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public List<ColumnsModel> getColumnsModels()
    {
        return columnsModels;
    }

    public void setColumnsModels(List<ColumnsModel> columnsModels)
    {
        this.columnsModels = columnsModels;
    }
}
