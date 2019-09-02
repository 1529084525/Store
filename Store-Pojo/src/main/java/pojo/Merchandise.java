package pojo;

import lombok.Data;

/**
 * 商品表
 */

@Data
public class Merchandise {

    private Integer mId;//自动增子列(商品表)

    private String merchandiseId;//商品ID

    private String merchandiseCommodity;//商品分类

    private String merchandiseMasterMap;//商品主图

    private String merchandiseTitle;//商品标题

    private Double merchandiseOriginalPrice;//商品原价

    private Double merchandisePresentPrice;//商品一口价

    private int merchandiseBrandId;//品牌id

}
