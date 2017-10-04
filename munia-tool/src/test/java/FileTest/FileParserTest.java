package FileTest;

import org.junit.Before;
import org.junit.Test;
import yyh.munia.com.fileParser.model.EFileType;
import yyh.munia.com.lagoTask.LegoTaskMgr;
import yyh.munia.com.util.FileTaskMagr;

/**
 * 测试txt读取
 * Created by oak on 2017/9/29.
 */
public class FileParserTest extends ABase
{


    /**
     *
     */
    @Before
    public void init()
    {

        super.everomentinit();

    }

    @Test
    public void txtTest()
    {

        FileTaskMagr fileTaskMagr = new FileTaskMagr(new TxtTravel(), EFileType.TXT_TYPE,"txtTest.txt");
        LegoTaskMgr.getInstance().submit(fileTaskMagr);

        try
        {
            Thread.sleep(100000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testXmlParser()
    {
        FileTaskMagr fileTaskMagr = new FileTaskMagr(new xmlTravel(), EFileType.XML_TYPE, "verification.xml");
        LegoTaskMgr.getInstance().submit(fileTaskMagr);

        try
        {
            Thread.sleep(100000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }


}
