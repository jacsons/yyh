package yyh.munia.parser.verification.model;

/**
 * Created by oak on 2017/10/3.
 */
public class ColumnModel
{
    private String id;

    private String nameZh;

    private String nameEn;

    private String type;

    private ConstrantModel constrantModel;

    private DependentModel dependentModel;

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

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public ConstrantModel getConstrantModel()
    {
        return constrantModel;
    }

    public void setConstrantModel(ConstrantModel constrantModel)
    {
        this.constrantModel = constrantModel;
    }

    public DependentModel getDependentModel()
    {
        return dependentModel;
    }

    public void setDependentModel(DependentModel dependentModel)
    {
        this.dependentModel = dependentModel;
    }
}
