package service.burse;

import interfaces.burse.BurseInterface;
import mapper.burse.BurseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pojo.Burse;

import java.util.List;

@Component
public class BurseService implements BurseInterface {
    @Autowired
    private BurseMapper burseMapper;

    @Override
    public List<Burse> selectAll() {
        return burseMapper.selectAll();
    }

    @Override
    public Burse selectById(int burseId) {
        return burseMapper.selectById(burseId);
    }

    @Override
    public int insertBurse(Burse burse) {
        return burseMapper.insertBurse(burse);
    }

    @Override
    public int deleteById(int burseId) {
        return burseMapper.deleteById(burseId);
    }

    @Override
    public int updateById(Burse burse) {
        return burseMapper.updateById(burse);
    }

    @Override
    public Burse selectByPhone(String phone) {
        return burseMapper.selectByPhone(phone);
    }

    @Override
    public int operationBalance(String phone, double money, int type) {
        return burseMapper.operationBalance(phone, money, type);
    }
}
