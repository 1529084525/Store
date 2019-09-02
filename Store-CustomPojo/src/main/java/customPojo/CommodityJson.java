package customPojo;

import lombok.Data;

import java.util.List;

@Data
public class CommodityJson {

    private String commodityId;//分类id

    private String commodityName;//分类名字

    private List<CommodityJson> commodityList;

}
