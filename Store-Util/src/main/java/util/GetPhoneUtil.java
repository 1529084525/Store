package util;

import javax.servlet.http.HttpServletRequest;

/**
 * 根据token获取用户手机号
 */
public class GetPhoneUtil {

    private GetPhoneUtil(){}

    public static String getPhone(HttpServletRequest request) throws Exception {
        return TokenUtil.parseJWT(request.getHeader("token")).get("phone").toString();
    }
}
