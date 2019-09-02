package service.merchandiseSKU;

import interfaces.merchandiseSKU.MerchandiseSKUInterface;
import mapper.merchandiseSKU.MerchandiseSKUMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pojo.MerchandiseSKU;
import pojo.ShoppingCar;

import java.util.List;

@Component
public class MerchandiseSKUService implements MerchandiseSKUInterface {
    @Autowired
    private MerchandiseSKUMapper merchandiseSKUMapper;

    @Override
    public int insertInfo(MerchandiseSKU merchandiseSKU) {
        return merchandiseSKUMapper.insertInfo(merchandiseSKU);
    }

    @Override
    public List<MerchandiseSKU> selectSizeById(String merchandiseId) {
        return merchandiseSKUMapper.selectSizeById(merchandiseId);
    }

    @Override
    public List<MerchandiseSKU> selectColorById(String merchandiseId) {
        return merchandiseSKUMapper.selectColorById(merchandiseId);
    }

    @Override
    public int selectCountById(String merchandiseId) {
        return merchandiseSKUMapper.selectCountById(merchandiseId);
    }

    @Override
    public int selectRepertoryByMerchandise(String merchandise, String color, String size) {
        return merchandiseSKUMapper.selectRepertoryByMerchandise(merchandise,color,size);
    }

    @Override
    public MerchandiseSKU selectByMerchandise(String merchandise, String color, String size) {
        return merchandiseSKUMapper.selectByMerchandise(merchandise,color,size);
    }

    @Override
    public MerchandiseSKU selectByShoppingCar(ShoppingCar shoppingCar) {
        return merchandiseSKUMapper.selectByShoppingCar(shoppingCar);
    }

    @Override
    public int selectMoneyByColor(String merchandiseId, String merchandiseColor) {
        return merchandiseSKUMapper.selectMoneyByColor(merchandiseId,merchandiseColor);
    }
}
