package interfaces.merchandiseSKU;

import org.apache.ibatis.annotations.Param;
import pojo.MerchandiseSKU;
import pojo.ShoppingCar;

import java.util.List;

public interface MerchandiseSKUInterface {

    /**
     * 添加详细+信息
     * @param merchandiseSKU
     * @return
     */
    int insertInfo(MerchandiseSKU merchandiseSKU);

    /**
     * 查询尺码
     * @param merchandiseId
     * @return
     */
    List<MerchandiseSKU> selectSizeById(String merchandiseId);

    /**
     * 查询颜色
     * @param merchandiseId
     * @return
     */
    List<MerchandiseSKU> selectColorById(String merchandiseId);

    /**
     * 查询总数量
     * @param merchandiseId
     * @return
     */
    int selectCountById(String merchandiseId);

    /**
     * 查询指定的库存
     * @param merchandise
     * @param color
     * @param size
     * @return
     */
    int selectRepertoryByMerchandise(@Param("id") String merchandise, @Param("c") String color, @Param("s") String size);

    /**
     * 查询制定数据
     * @param merchandise
     * @param color
     * @param size
     * @return
     */
    MerchandiseSKU selectByMerchandise(@Param("id") String merchandise, @Param("c") String color, @Param("s") String size);

    /**
     * 查询
     * @param shoppingCar
     * @return
     */
    MerchandiseSKU selectByShoppingCar(ShoppingCar shoppingCar);

    /**
     * 根据商品编号+选择颜色查询具体价格
     * @param merchandiseId
     * @param merchandiseColor
     * @return
     */
    int selectMoneyByColor(String merchandiseId,String merchandiseColor);


}
