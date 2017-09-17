package yyh.munia.com.prgress.inf;

/**
 * Created by oak on 2017/9/17.
 */
public interface IProgressOperator
{
    /**
     * 写
     * @param modelKey
     * @param value
     */
    void write (String modelKey, String value);

    /**
     * 读
     * @param modelKey
     */
    String read(String modelKey);

    /**
     * 清除
     * @param modelKey
     * @return
     */
    String clear(String modelKey);
}
