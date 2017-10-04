package yyh.munia.com.fileParser.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by oak on 2017/8/18.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FileIndent
{

    /**
     *
     * 文件类型
     * @return
     *
     */
    String[] fileType() default {"txt"};

    /**
     *
     * 自定以解析类
     * @return
     *
     */
    Class<?> parseType();

}
