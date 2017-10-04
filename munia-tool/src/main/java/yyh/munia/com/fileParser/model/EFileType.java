package yyh.munia.com.fileParser.model;

/**
 * 文件类型枚举
 * Created by oak on 2017/8/23.
 */
public enum EFileType
{

    TXT_TYPE(0,"txt"),

    XML_TYPE(1,"xml");

    private int typeID;

    private String subfix;


    EFileType(int typeID,String subfix)
    {
        this.typeID = typeID;
        this.subfix = subfix;
    }

    public int getTypeID()
    {
        return typeID;
    }

    public void setTypeID(int typeID)
    {
        this.typeID = typeID;
    }

    public String getSubfix()
    {
        return subfix;
    }

    public void setSubfix(String subfix)
    {
        this.subfix = subfix;
    }
}
