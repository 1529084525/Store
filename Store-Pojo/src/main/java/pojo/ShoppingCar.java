package pojo;

import lombok.Data;

/**
 * 购物车
 */
@Data
public class ShoppingCar {

    private Integer shoppingCarId;
    private String userPhone;
    private String merchandiseId;
    private String merchandiseColor;
    private String merchandiseSize;
    private Integer merchandiseCount;
    private Double merchandiseMoney;
    private String shoppingCarTime;
}
