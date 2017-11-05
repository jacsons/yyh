package yyh.munia.parser.parser.model;

/**
 * 解析过程中的一些状态信息
 * Created by oak on 2017/10/30.
 */
public enum EHandlerMsg
{
    MA(-1,"unkonw"),

    FAILED(0, "parser process faild"),

    NEW(1, "new row information"),

    SUCCESS(2,"process success");

    private int code;

    private String decription;

    EHandlerMsg(int code, String decription)
    {
        this.code = code;
        this.decription = decription;
    }
}
