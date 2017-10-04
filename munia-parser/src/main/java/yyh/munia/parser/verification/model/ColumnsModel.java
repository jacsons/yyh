package yyh.munia.parser.verification.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oak on 2017/10/3.
 */
public class ColumnsModel
{
    private String id;

    private List<ColumnModel> columnModels = new ArrayList<ColumnModel>();

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public List<ColumnModel> getColumnModels()
    {
        return columnModels;
    }

    public void setColumnModels(List<ColumnModel> columnModels)
    {
        this.columnModels = columnModels;
    }
}
