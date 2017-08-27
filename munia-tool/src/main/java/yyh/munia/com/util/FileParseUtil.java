package yyh.munia.com.util;

/**
 * Created by oak on 2017/8/18.
 */
public class FileParseUtil
{






   public static class LazyInstall
   {
       public static final FileParseUtil INSTANCE = new FileParseUtil();

       public static FileParseUtil getIntance()
       {
           return INSTANCE;

       }
   }
}
