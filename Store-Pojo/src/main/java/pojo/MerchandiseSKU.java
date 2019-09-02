package pojo;

import lombok.Data;

/**
 * 商品数据
 */
@Data
public class MerchandiseSKU {

    private Integer merchandiseSKUId;//数据自增列

    private String merchandiseId;//商品id

    private String merchandiseColor;//商品颜色

    private String merchandiseImg;

    private String merchandiseSize;//商品尺码

    private Integer merchandiseCount;//库存

    private Double merchandiseMoney;//商品真实价格
}
