package pojo;

import lombok.Data;

@Data
public class RealNameAuthentication {

    private Integer authenticationId;
    private String userPhone;
    private String authenticationName;
    private String authenticationCode;
    private String authenticationImage;
}
