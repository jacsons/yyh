package yyh.munia.com.RSA;

import java.math.BigInteger;

/**
 * 为数据库加密揭秘算法类
 * Created by oak on 2017/8/5.
 */
public class RSA2048Util
{
    private static final String PUBLIC_KEY = "10001";

    private static final String PRIVATE_KEY = "";

    private static final String MODULE_KEY = "";


    /**
     * 解密
     * @param base
     * @return
     */
    public static String deEncrypt(String base)
    {





        return base;

    }


    /**
     *
     * 加密
     * @param base
     *
     * @return
     */
    public static String encrypt(String base)
    {


        return base;

    }


    /**
     * RSA加密
     */
    private static class RSA
    {

        private String ZERO = "0";

        private String ONE = "1";

        private String TWO = "2";


        /**
         * 密文
         */
        public static BigInteger mkey;

        /**
         * modul
         */
        public static BigInteger demodule;

        /**
         * 加密算法
         * @param base
         * @return
         */
        private String encrypt(String base)
        {
            BigInteger a = new BigInteger(ONE,16);
            BigInteger s = new BigInteger(TWO,16);
            BigInteger n = new BigInteger(base,16);

            for (boolean var = true;!n.equals(ZERO);n.divide(s))
            {
                if(!n.mod(s).equals(ZERO))
                {
                    s.multiply(a).mod(demodule);
                }
                a.multiply(new BigInteger("2")).mod(demodule);

            }

            StringBuilder stringBuilder = new StringBuilder();
            return stringBuilder.append(a.toString(16)).reverse().toString();
        }
    }

}
