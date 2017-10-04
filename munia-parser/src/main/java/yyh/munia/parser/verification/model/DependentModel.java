package yyh.munia.parser.verification.model;

/**
 * 列依赖的部分
 * Created by oak on 2017/10/3.
 */
public class DependentModel
{
    private String templateId;

    private String columns;

    private String column;

    public String getTemplateId()
    {
        return templateId;
    }

    public void setTemplateId(String templateId)
    {
        this.templateId = templateId;
    }

    public String getColumns()
    {
        return columns;
    }

    public void setColumns(String columns)
    {
        this.columns = columns;
    }

    public String getColumn()
    {
        return column;
    }

    public void setColumn(String column)
    {
        this.column = column;
    }
}
