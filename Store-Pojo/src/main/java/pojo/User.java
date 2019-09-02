package pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户表
 */

@Data
public class User {

    private Integer addressId;//地址id

    private String userPhone;//用户手机号码

    private String userPassword;//用户密码

    private String userSex;//用户性别

    private String userBirthday;//用户生日

    private String userPhoto;//用户头像

    private String userTime;//注册时间

    private Integer gradeId; //用户等级

}
