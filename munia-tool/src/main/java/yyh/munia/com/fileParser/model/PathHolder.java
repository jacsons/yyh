package yyh.munia.com.fileParser.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 存储文件路径
 * Created by oak on 2017/8/18.
 */
public class PathHolder
{

    /**
     * 存储配置文件路径
     */
    public List<String> paths= new ArrayList<String>();


    /**
     *
     */
    private int current = 0;

    /**
     * 是否有下一个
     * @return
     */
    public boolean hasNext()
    {
        return current < paths.size();
    }

    /**
     * 下一个
     * @return
     */
    public String next()
    {
        if (hasNext())
        {
            return paths.get(current ++);
        }
        return "";
    }

    /**
     * 添加path
     * @param path
     */
    public void addPath(String path)
    {
        paths.add(path);
    }

    /**
     * 清空路径库
     * @return
     */
    public void clearAll()
    {
        current = 0;
        paths.clear();
    }

    /**
     * 清空，又从0开始
     */
    public void reset()
    {
        current = 0;
    }
}
