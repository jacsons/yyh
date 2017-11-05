package yyh.munia.parser.parser.model;

/**
 *
 * Created by oak on 2017/11/5.
 */
public enum  EfileType
{

    NA("unkonw", -1),

    COURT_INFO("courtinfo",1);


    /**
     * 文件标识符号
     */
    private String token;

    /**
     * code
     */
    private int code;

    /**
     * 是否缓存
     */
    private boolean isCache = true;

    /**
     * 依赖向
     */
    private int[] denpendenCode;


    /**
     *
     * @param token
     * @param code
     */
    private EfileType(String token,int code)
    {
        this.token = token;
        this.code = code;
    }
    /**
     *
     * @param token
     * @param code
     * @param isCache
     */
    private EfileType(String token,int code,boolean isCache)
    {
        this.token = token;
        this.code = code;
        this.isCache = isCache;
    }

    /**
     *
     * @param token
     * @param code
     * @param isCache
     * @param denpendenCodes
     */
    private EfileType(String token,int code,boolean isCache,int...denpendenCodes)
    {
        this.token = token;
        this.code = code;
        this.isCache = isCache;
        this.denpendenCode = denpendenCodes;
    }

}
