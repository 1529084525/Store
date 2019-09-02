package customPojo;

import lombok.Data;

/**
 * 查询
 */
@Data
public class MerchandiseBySearch {

    private Integer mId;
    private String merchandiseId;
    private String merchandiseMasterMap;
    private String merchandiseTitle;
    private Double merchandisePresentPrice;
    private Integer merchandiseSalesCount;
}
