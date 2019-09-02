package customPojo;

import lombok.Data;

/**
 * 购物车查询
 */
@Data
public class MerchandiseShoppingCar {
    private Integer shoppingCarId;
    private String userPhone;
    private String merchandiseId;
    private String merchandiseColor;
    private String merchandiseSize;
    private Integer merchandiseCount;
    private Double money1;
    private String merchandiseTitle;
    private String merchandiseImg;
    private Double money2;
}
