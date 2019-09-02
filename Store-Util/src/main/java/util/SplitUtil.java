package util;

public abstract class SplitUtil {

    /**
     * 根据字符分割字符
     * @param str
     * @param rex
     * @return
     */
    public static String[] getSplitStr(String str, String rex) {
        String[] splitStr = null;
        if (isLetterDigitOrChinese(rex)) {
            splitStr = str.split(rex);
        } else {
            StringBuffer oneA = new StringBuffer("\\");
            splitStr = str.split(oneA.append(rex).toString());
        }
        return splitStr;
    }


    /**
     * 判断是否是字符
     *
     * @param str
     * @return
     */
    private static boolean isLetterDigitOrChinese(String str) {
        String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";//其他需要，直接修改正则表达式就好
        return str.matches(regex);
    }


}
