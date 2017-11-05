package yyh.munia.parser.parser.anno;

import yyh.munia.parser.parser.model.EfileType;

import java.lang.annotation.*;

/**
 * Created by oak on 2017/11/5.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FileIdentify
{
    /**
     * 文件类型
     * @return
     */
    EfileType EfileType() default EfileType.COURT_INFO;

    /**
     * 文件类型
     * @return
     */
    String[] suffix() default {"xlxs"};
}
