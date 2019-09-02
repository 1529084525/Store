package service.tradingRecode;

import customPojo.TradingRecodeByThree;
import interfaces.tradingRecode.TradingRecodeInterface;
import mapper.tradingRecode.TradingRecodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pojo.TradingRecode;

import java.util.List;

@Component
public class TradingRecodeService implements TradingRecodeInterface {

    @Autowired
    private TradingRecodeMapper tradingRecodeMapper;

    @Override
    public int insertRecode(TradingRecode tradingRecode) {
        return tradingRecodeMapper.insertRecode(tradingRecode);
    }

    @Override
    public List<TradingRecode> selectByPhone(String phone, int page) {
        return tradingRecodeMapper.selectByPhone(phone,page);
    }

    @Override
    public List<TradingRecodeByThree> selectAllByPhone(String phone, int page) {
        return tradingRecodeMapper.selectAllByPhone(phone,page);
    }

    @Override
    public int selectCountByPhone(String phone) {
        return tradingRecodeMapper.selectCountByPhone(phone);
    }
}
