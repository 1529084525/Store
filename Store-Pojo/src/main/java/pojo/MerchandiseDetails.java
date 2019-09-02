package pojo;

import lombok.Data;

/**
 * 商品详情
 */

@Data
public class MerchandiseDetails {

    private Integer merchandiseDetailsId;//商品详情自增列

    private String merchandiseId;//商品id

    private String merchandiseHeaderImages;//展示图片

    private String merchandiseFooterContent;//商品详情页

    private Integer merchandiseSalesCount;//商品销量

    private Integer merchandisePresentIntegral;//商品赠送积分数

    private String merchandiseWeight;//商品材质

    private String merchandiseOnTime;//上架时间

    private String merchandiseOutTime;//下架时间

    private Integer merchandiseFreight;//运费
}
