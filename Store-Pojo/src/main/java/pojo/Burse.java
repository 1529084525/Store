package pojo;

import lombok.Data;

/**
 * 钱包
 */

@Data
public class Burse {

    private Integer burseId;//钱包id

    private String userPhone;//用户手机号

    private String burseBalance;//钱包余额

    public Burse() {
    }

    public Burse(String userPhone) {
        this.userPhone = userPhone;
    }
}
