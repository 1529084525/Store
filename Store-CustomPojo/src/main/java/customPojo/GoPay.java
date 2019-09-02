package customPojo;

import lombok.Data;

/**
 * 立即支付调用
 */
@Data
public class GoPay {

/*
    private Merchandise merchandise;
    private MerchandiseDetails merchandiseDetails;
    private MerchandiseSKU merchandiseSKU;
*/

    private String merchandiseId;//商品ID

    private String merchandiseTitle;//商品标题

    private int merchandisePresentIntegral;//商品赠送积分数

    private int merchandiseFreight;//运费

    private String merchandiseColor;//商品颜色

    private String merchandiseImg;//商品图片(对应颜色)

    private String merchandiseSize;//商品尺码

    private int merchandiseCount;//库存

    private Double merchandiseMoney;//商品真实价格



}
