package interfaces.shoppingCar;

import customPojo.MerchandiseShoppingCar;
import org.apache.ibatis.annotations.Param;
import pojo.ShoppingCar;

import java.util.List;

public interface ShoppingCarInterface {

    /**
     * 添加一个购物车
     *
     * @param shoppingCar
     * @return
     */
    int insertCar(ShoppingCar shoppingCar);

    /**
     * 根据手机号查询数量
     * @param phone
     * @return
     */
    int selectCountByPhone(String phone, int type);

    /**
     * 根据手机号码查询购物车
     *
     * @param phone
     * @return
     */
    List<MerchandiseShoppingCar> selectAllByPhone(String phone,int type);

    /**
     * 查询未登录的信息
     *
     * @param shoppingCar
     * @return
     */
    List<MerchandiseShoppingCar> selectAllByNoLogin(List<ShoppingCar> shoppingCar);

    /**
     * 添加到未登录
     * @param shoppingCar
     * @return
     */
    int insertByLogin(ShoppingCar shoppingCar);

    /**
     * 查询
     * @param phone
     * @return
     */
    List<ShoppingCar> selectAll(@Param("phone") String phone,@Param("type")int type);

    /**
     * 删除全部
     *
     * @param phone
     * @param type
     * @return
     */
    int deleteAll(@Param("phone") String phone, @Param("type") int type);

    /**
     * 判断是否存在
     * @param shoppingCar
     * @return
     */
    ShoppingCar checkExist(@Param("shoppingCar") ShoppingCar shoppingCar,@Param("type")int type);

    /**
     * 修改数据
     * @param shoppingCar
     * @return
     */
    int updateCar(@Param("shoppingCar") ShoppingCar shoppingCar,@Param("type")int typeId);

    /**
     * 根据id查询详情
     * @param id
     * @return
     */
    ShoppingCar selectById(int id);
}
