package yyh.munia.parser.verification.model;

/**
 * Created by oak on 2017/10/3.
 */
public enum  ColumnTypeEnum
{
    STRING("String","s*");

    /**
     *
     * @param type
     * @param pattern
     */
    ColumnTypeEnum(String type, String pattern)
    {
        this.type = type;
        this.pattern = pattern;
    }

    private String type;

    private String pattern;
}
