package pojo;

import lombok.Data;

/**
 * 保存未登录的购物车
 */
@Data
public class ShoppingCarByNoLogin {
    private Integer shoppingCarId;
    private String userPhone;
    private String merchandiseId;
    private String merchandiseColor;
    private String merchandiseSize;
    private Integer merchandiseCount;
    private Double merchandiseMoney;
    private String shoppingCarTime;
}
