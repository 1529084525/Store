package service.paymentCode;

import interfaces.paymentCode.PaymentCodeInterface;
import mapper.paymentCode.PaymentCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pojo.PaymentCode;

@Component
public class PaymentCodeService implements PaymentCodeInterface {

    @Autowired
    private PaymentCodeMapper paymentCodeMapper;

    @Override
    public int insertPayment(PaymentCode paymentCode) {
        return paymentCodeMapper.insertPayment(paymentCode);
    }

    @Override
    public int checkExist(String phone) {
        return paymentCodeMapper.checkExist(phone);
    }

    @Override
    public int checkTure(PaymentCode paymentCode) {
        return paymentCodeMapper.checkTure(paymentCode);
    }

    @Override
    public int updatePayment(PaymentCode paymentCode) {
        return paymentCodeMapper.updatePayment(paymentCode);
    }

    @Override
    public PaymentCode selectByPhone(String phone) {
        return paymentCodeMapper.selectByPhone(phone);
    }

    @Override
    public int deleteByPhone(String phone) {
        return paymentCodeMapper.deleteByPhone(phone);
    }
}
