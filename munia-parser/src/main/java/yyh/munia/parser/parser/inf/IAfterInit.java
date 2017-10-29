package yyh.munia.parser.parser.inf;

/**
 * 初始化后的接口
 * Created by oak on 2017/10/27.
 */
public interface IAfterInit<T>
{

    /**
     *
     * @return
     */
    boolean afterInit(T handler);

}
