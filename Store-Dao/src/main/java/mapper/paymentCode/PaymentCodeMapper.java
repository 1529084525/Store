package mapper.paymentCode;

import pojo.PaymentCode;

public interface PaymentCodeMapper {

    /**
     * 添加一个支付密码
     *
     * @param paymentCode
     * @return
     */
    int insertPayment(PaymentCode paymentCode);

    /**
     * 根据手机号码查询是否存在
     *
     * @param phone
     * @return
     */
    int checkExist(String phone);

    /**
     * 判断是否正确
     *
     * @param paymentCode
     * @return
     */
    int checkTure(PaymentCode paymentCode);

    /**
     * 更新支付密码
     *
     * @param paymentCode
     * @return
     */
    int updatePayment(PaymentCode paymentCode);

    /**
     * 根据手机号查询
     * @param phone
     * @return
     */
    PaymentCode selectByPhone(String phone);

    /**
     * 根据手机号码删除
     * @param phone
     * @return
     */
    int deleteByPhone(String phone);
}
