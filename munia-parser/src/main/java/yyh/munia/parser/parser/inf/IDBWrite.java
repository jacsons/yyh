package yyh.munia.parser.parser.inf;

import java.util.List;

/**
 * Created by oak on 2017/11/5.
 */
public interface IDBWrite
{
    /**
     * 插入对象
     * @param objects
     */
    void insert(List<Object> objects);

    /**
     * 创建表格
     */
    void createTbale();
}
