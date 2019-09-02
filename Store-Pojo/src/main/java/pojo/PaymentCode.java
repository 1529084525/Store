package pojo;

import lombok.Data;

/**
 * 支付密码
 */
@Data
public class PaymentCode {

    private Integer paymentCodeId;
    private String userPhone;
    private String paymentPassword;
    private String paymentCodeTime;

}
