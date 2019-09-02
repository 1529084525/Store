package pojo;

import lombok.Data;

/**
 * 地址表
 */

@Data
public class Address {

    private Integer addressId;//地址id

    private String userPhone;//用户手机号

    private String consignee;//收货人

    private String location;//收货地址

    private String detailsAddress;//详细地址

    private String consigneePhone;//收货人手机号

    private String defaultAddress;//默认地址

}
