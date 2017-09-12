package yyh.dbservice.com.db.liquibase;

/**
 *
 * 创建liqui数据库用的类
 *
 * Created by oak on 2017/9/10.
 */
public class LiquiConstant
{
    /**
     * 查询是否有数据表
     */
    public static final String QUERY_LIQUI_DB = "show tables like liquiChangeLogs";

    public static final String CREATE_LIQUI_DB = "create liqui";

    public static final String DROP_LIQUI_DB = "Drop database liquiChangeLogs";



}
