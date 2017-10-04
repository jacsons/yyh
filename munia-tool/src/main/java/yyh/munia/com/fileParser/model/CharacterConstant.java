package yyh.munia.com.fileParser.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件字符常量
 * Created by oak on 2017/8/26.
 */
public class CharacterConstant
{
    public static final String SYSTEM_CODING = System.getProperty("sun.jnu.encoding");


    public static List<String> SUPPORT_CODING = new ArrayList<>();


    static
    {
        SUPPORT_CODING.add("UTF-8");

    }

}
