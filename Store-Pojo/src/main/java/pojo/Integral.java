package pojo;

import lombok.Data;

@Data
public class Integral {
    private Integer integralId;
    private String userPhone;
    private Double integralBalance;

    public Integral(String userPhone, Double integralBalance) {
        this.userPhone = userPhone;
        this.integralBalance = integralBalance;
    }

    public Integral() {
    }
}
