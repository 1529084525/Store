package service.integral;

import interfaces.integral.IntegralInterface;
import mapper.integral.IntegralMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pojo.Integral;

@Component
public class IntegralService implements IntegralInterface {

    @Autowired
    private IntegralMapper integralMapper;

    @Override
    public int insertIntegral(Integral integral) {
        return integralMapper.insertIntegral(integral);
    }

    @Override
    public int operationIntegral(String phone, double integral, int type) {
        return integralMapper.operationIntegral(phone,integral,type);
    }

    @Override
    public Integral selectByPhone(String phone) {
        return integralMapper.selectByPhone(phone);
    }
}
