package util;

public abstract class VeriflyAuthCodeUtil {

    /**
     * 验证滑块
     *
     * @param ticket
     * @param randstr
     * @return
     */
    public static String Verifly(String ticket, String randstr) {
        return UrlUtil.sendGet("https://ssl.captcha.qq.com/ticket/verify",
                "aid=2053053817&" +
                        "AppSecretKey=0FNMHtTsDDsJtPBo2fidl5Q**&" +
                        "Ticket=" + ticket + "&" +
                        "Randstr=" + randstr + "&" +
                        "UserIP=10.127.10.2");
    }

}
