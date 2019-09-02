package customPojo;

import lombok.Data;
import pojo.Merchandise;
import pojo.MerchandiseDetails;

/**
 * 连表查询商品数据
 */
@Data
public class Merchandise_Details {

    private Merchandise merchandise;
    private MerchandiseDetails merchandiseDetails;

}
