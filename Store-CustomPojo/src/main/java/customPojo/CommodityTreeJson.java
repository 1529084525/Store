package customPojo;

import lombok.Data;

import java.util.List;

/**
 * 树形数据
 */
@Data
public class CommodityTreeJson {

    private String id;
    private String title;
    private List<CommodityTreeJson> children;

}
