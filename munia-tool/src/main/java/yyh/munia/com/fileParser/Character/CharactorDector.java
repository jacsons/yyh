package yyh.munia.com.fileParser.Character;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.mozilla.intl.chardet.nsDetector;
import org.mozilla.intl.chardet.nsICharsetDetectionObserver;
import org.mozilla.intl.chardet.nsPSMDetector;
import yyh.munia.com.fileParser.model.CharacterConstant;
import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;

import java.io.*;


/**
 * 文件类型识别类
 * Created by oak on 2017/8/23.
 */
public class CharactorDector
{

    /**
     * 判断是否找到
     */
    private boolean found = false;

    /**
     * 字符类型结果
     */
    private String character;


    /**
     * 检车文件
     * @param inputStream
     * @return
     */
    public String detect(InputStream inputStream)
    {
        String charactor = null;
        try
        {
            charactor = detectCharactor(new nsDetector(nsPSMDetector.ALL), inputStream);
        }
        catch (IOException e)
        {
            LoggerManager.record(LoggerType.ERROR,"detect charactor failed");
        }
        return charactor;

    }

    /**
     * 检测文件
     * @param filePath
     * @return
     */
    public  String detect(String filePath)
    {
        String character = null;
        if(StringUtils.isEmpty(filePath))
        {
            LoggerManager.record(LoggerType.INFO,"filePath is empty");
        }


        File file = FileUtils.getFile(filePath);
        if(!file.exists())
        {
            file = FileUtils.getFile(System.getProperty("user.dir") + filePath);
        }

        //以文件流的方式，
        InputStream inputStream = null;
        try
        {
            inputStream = new FileInputStream(file);
            character = detectCharactor(new nsDetector(nsPSMDetector.ALL), inputStream);
        }
        catch (FileNotFoundException e)
        {
            LoggerManager.record(LoggerType.INFO,"IOException error");
        }
        catch (IOException e)
        {
            LoggerManager.record(LoggerType.INFO,"can't open the fileParser");
        }
        finally
        {
            IOUtils.closeQuietly(inputStream);
        }

        return character == null ? CharacterConstant.SYSTEM_CODING : character;
    }


    /**
     * 检车文件类型
     * @return
     */
    private String detectCharactor(nsDetector detector, InputStream inputStream) throws IOException
    {
        detector.Init(new nsICharsetDetectionObserver()
        {
            @Override
            public void Notify(String s)
            {
                found = true;
                character = s;
                LoggerManager.record(LoggerType.INFO,"found charactor is :" + s);
            }
        });

        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        byte[] buffer = new byte[1024];
        int len;
        boolean done = false;
        boolean isAscii = true;

        while ((len = bufferedInputStream.read(buffer, 0, buffer.length))!=-1)
        {

            if (isAscii)
            {
                isAscii=detector.isAscii(buffer, len);
            }
            if (!isAscii && !done)
            {
                done=detector.DoIt(buffer, len, false);
            }
        }

        String[] probablys = detector.getProbableCharsets();


        detector.DataEnd();

        return found ? character : guessProbably(probablys);
    }


    /**
     * 获取可能的字符类型
     * @param probablys
     * @return
     */
    public String guessProbably(String[] probablys)
    {
        for(String str : probablys)
        {
            if (CharacterConstant.SUPPORT_CODING.contains(str))
            {
                return str;
            }
        }
        return CharacterConstant.SYSTEM_CODING;
    }

}
