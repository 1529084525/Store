package pojo;

import lombok.Data;

/**
 * 品牌类
 */

@Data
public class Brand {

    private Integer brandId;//品牌id

    private String brandName;//品牌名称

    private String brandLogo;//品牌logo

    private String brandAddress;//品牌地址

    private String brandComment;//品牌详情

    private String brandTime;//品牌时间
}
