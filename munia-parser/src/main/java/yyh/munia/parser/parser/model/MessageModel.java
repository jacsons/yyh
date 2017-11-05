package yyh.munia.parser.parser.model;

/**
 * Created by oak on 2017/10/30.
 */
public class MessageModel
{

    /**
     * 行信息
     */
    private int col;

    /**
     * excel可能存在多个sheet页；
     */
    private int sheet;

    /**
     * 数据类型
     */
    private String type;

    /**
     * 信息体
     */
    private Object datas;


    /**
     * 解析状态
     */
    private EHandlerMsg eHandlerMsg;

    public int getCol()
    {
        return col;
    }

    public void setCol(int col)
    {
        this.col = col;
    }

    public int getSheet()
    {
        return sheet;
    }

    public void setSheet(int sheet)
    {
        this.sheet = sheet;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public Object getDatas()
    {
        return datas;
    }

    public void setDatas(Object datas)
    {
        this.datas = datas;
    }

    public EHandlerMsg geteHandlerMsg()
    {
        return eHandlerMsg;
    }

    public void seteHandlerMsg(EHandlerMsg eHandlerMsg)
    {
        this.eHandlerMsg = eHandlerMsg;
    }
}
