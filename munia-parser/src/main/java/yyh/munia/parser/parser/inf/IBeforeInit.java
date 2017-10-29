package yyh.munia.parser.parser.inf;

/**
 * Created by oak on 2017/10/27.
 */
public interface IBeforeInit<T>
{
    /**
     * 解析前的init类
     * @return
     */
    boolean beforeInit(T handler);
}
