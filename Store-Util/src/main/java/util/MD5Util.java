package util;

import org.apache.commons.codec.digest.DigestUtils;

public abstract class MD5Util {
    public static final String KEY = "bkajsljagw rag fiagf lajg aishg foahg r loag vflakh fvlakl hgakl ahsc o;as .2359238 9t85v u3ywgk 3y4 e36 4dh4d3b5sfgaynwcq jgfsfhlkstgv";

    /**
     * MD5方法
     *
     * @param text 明文
     * @param key  密钥
     * @return 密文
     * @throws Exception
     */
    public static String md5(String text, String key) throws Exception {
        //加密后的字符串
        return DigestUtils.md5Hex(text + key);
    }

    /**
     * MD5验证方法
     *
     * @param text 明文
     * @param key  密钥
     * @param md5  密文
     * @return true/false
     * @throws Exception
     */
    public static boolean verify(String text, String key, String md5) throws Exception {
        //根据传入的密钥进行验证
        String md5Text = md5(text, key);
        if (md5Text.equalsIgnoreCase(md5)) {
            System.out.println("MD5验证通过");
            return true;
        }

        return false;
    }
}
