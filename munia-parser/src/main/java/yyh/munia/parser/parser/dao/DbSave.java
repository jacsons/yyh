package yyh.munia.parser.parser.dao;

import java.util.List;

/**
 * 保存数据的类
 * Created by oak on 2017/10/14.
 */
public interface DbSave
{
    /**
     * 插入数据
     * @param objectss
     */
    void insert(List<Object> objectss);

    /**
     * 创建表
     * @param table
     */
    void createTable(String table);

}
