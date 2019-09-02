package service.realNameAuthentication;

import interfaces.realNameAuthentication.RealNameAuthenticationInterface;
import mapper.realNameAuthentication.RealNameAuthenticationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pojo.RealNameAuthentication;

@Component
public class RealNameAuthenticationService implements RealNameAuthenticationInterface {

    @Autowired
    private RealNameAuthenticationMapper realNameAuthenticationMapper;

    @Override
    public int insertAuthentication(RealNameAuthentication realNameAuthentication) {
        return realNameAuthenticationMapper.insertAuthentication(realNameAuthentication);
    }

    @Override
    public int checkExist(String code) {
        return realNameAuthenticationMapper.checkExist(code);
    }

    @Override
    public RealNameAuthentication selectOne(String phone) {
        return realNameAuthenticationMapper.selectOne(phone);
    }
}
