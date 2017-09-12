package yyh.dbservice.com.db;

import org.apache.ibatis.mapping.DatabaseIdProvider;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 提供一个接口，不知道意义何在
 *
 * Created by oak on 2017/9/10.
 */
public class DBProvider implements DatabaseIdProvider
{
    @Override
    public void setProperties(Properties properties)
    {

    }

    @Override
    public String getDatabaseId(DataSource dataSource) throws SQLException
    {
        return null;
    }
}
