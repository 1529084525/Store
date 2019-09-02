package interfaces.realNameAuthentication;

import pojo.RealNameAuthentication;

public interface RealNameAuthenticationInterface {
    /**
     * 添加实名认证
     *
     * @param realNameAuthentication
     * @return
     */
    int insertAuthentication(RealNameAuthentication realNameAuthentication);

    /**
     * 判断此身份证是否被注册
     *
     * @param code
     * @return
     */
    int checkExist(String code);

    /**
     * 根据手机号码查询
     *
     * @param phone
     * @return
     */
    RealNameAuthentication selectOne(String phone);
}
