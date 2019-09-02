package pojo;

import lombok.Data;

/**
 * 收藏表
 */

@Data
public class Collect {

    private Integer collectId;//收藏id

    private String merchandiseId;//商品id

    private String userPhone;//用户手机号
}
