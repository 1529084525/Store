package interfaces.integral;

import org.apache.ibatis.annotations.Param;
import pojo.Integral;

public interface IntegralInterface {

    /**
     * 添加一个
     *
     * @param integral
     * @return
     */
    int insertIntegral(Integral integral);

    /**
     * 操作积分
     *
     * @param phone
     * @param integral
     * @param type 0 - 加钱  / 1 - 减钱
     * @return
     */
    int operationIntegral(@Param("phone") String phone, @Param("integral") double integral, @Param("type") int type);

    /**
     * 根据手机号码查询
     * @param phone
     * @return
     */
    Integral selectByPhone(String phone);

}
