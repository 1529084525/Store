package pojo;

import lombok.Data;

/**
 * 人脸表
 */

@Data
public class Face {

    private Integer faceId;//人脸id

    private String faceToken;//人脸Token

    private String userPhone;//用户手机号
}
