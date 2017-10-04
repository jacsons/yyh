package yyh.munia.com.fileParser.util;

import yyh.munia.com.fileParser.Character.CharactorDector;

import java.io.InputStream;

/**
 * 输入输出流
 * Created by oak on 2017/8/26.
 */
public class StreamUtil
{

    /**
     *
     * 返回字符流
     * @param inputStream
     * @return
     */
    public static String detectCharacter(InputStream inputStream)
    {

        CharactorDector charactorDector = new CharactorDector();
        String encoding = charactorDector.detect(inputStream);

        return encoding;
    }

    /**
     * 检测
     * @param filePath
     * @return
     */
    public static String detectCharacter(String filePath)
    {
        CharactorDector charactorDector = new CharactorDector();
        String encoding = charactorDector.detect(filePath);
        return encoding;
    }

}
