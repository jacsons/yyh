package yyh.munia.com.util.redis.model;

/**
 * Created by oak on 2017/7/27.
 */
public class ConfigUtil
{

    /**
     * 最大空闲时间
     */
    private Integer maxIdle;

    /**
     * 对大连接数
     */
    private Integer maxTatal;

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Integer getMaxTatal() {
        return maxTatal;
    }

    public void setMaxTatal(Integer maxTatal) {
        this.maxTatal = maxTatal;
    }

}
