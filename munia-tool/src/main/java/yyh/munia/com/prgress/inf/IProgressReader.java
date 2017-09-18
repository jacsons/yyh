package yyh.munia.com.prgress.inf;

/**
 * 读操作接口
 * Created by oak on 2017/9/14.
 */
public interface IProgressReader<T> extends IProgressKey
{
    /**
     * 获取进度消息
     * @param
     */
    T reapProgress();

}
