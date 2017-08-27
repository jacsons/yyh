package yyh.munia.com.file.Character;

import java.io.IOException;
import java.io.InputStream;

/**
 * 含有字符类型的
 * Created by oak on 2017/8/23.
 */
public class CharactorInputStream extends InputStream
{
    private String charactor;

    private InputStream inputStream;


    public CharactorInputStream(String charactor,InputStream inputStream)
    {
        this.charactor = charactor;
        this.inputStream = inputStream;

    }

    @Override
    public int read() throws IOException
    {
        return inputStream.read();
    }


    public String getCharactor()
    {
        return charactor;
    }

    public void setCharactor(String charactor)
    {
        this.charactor = charactor;
    }

    public InputStream getInputStream()
    {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream)
    {
        this.inputStream = inputStream;
    }
}
