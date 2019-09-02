package service.shoppingCar;

import customPojo.MerchandiseShoppingCar;
import interfaces.shoppingCar.ShoppingCarInterface;
import mapper.shoppingCar.ShoppingCarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pojo.ShoppingCar;

import java.util.List;

/**
 * 购物车
 */
@Component
public class ShoppingCarService implements ShoppingCarInterface {

    @Autowired
    private ShoppingCarMapper shoppingCarMapper;

    @Override
    public int insertCar(ShoppingCar shoppingCar) {
        return shoppingCarMapper.insertCar(shoppingCar);
    }

    @Override
    public int selectCountByPhone(String phone, int type) {
        return shoppingCarMapper.selectCountByPhone(phone,type);
    }

    @Override
    public List<MerchandiseShoppingCar> selectAllByPhone(String phone,int type) {
        return shoppingCarMapper.selectAllByPhone(phone,type);
    }

    @Override
    public List<MerchandiseShoppingCar> selectAllByNoLogin(List<ShoppingCar> shoppingCar) {
        return shoppingCarMapper.selectAllByNoLogin(shoppingCar);
    }

    @Override
    public int insertByLogin(ShoppingCar shoppingCar) {
        return shoppingCarMapper.insertByLogin(shoppingCar);
    }

    @Override
    public List<ShoppingCar> selectAll(String phone,int type) {
        return shoppingCarMapper.selectAll(phone,type);
    }

    @Override
    public int deleteAll(String phone, int type) {
        return shoppingCarMapper.deleteAll(phone,type);
    }

    @Override
    public ShoppingCar checkExist(ShoppingCar shoppingCar, int type) {
        return shoppingCarMapper.checkExist(shoppingCar,type);
    }

    @Override
    public int updateCar(ShoppingCar shoppingCar, int typeId) {
        return shoppingCarMapper.updateCar(shoppingCar,typeId);
    }

    @Override
    public ShoppingCar selectById(int id) {
        return shoppingCarMapper.selectById(id);
    }
}
