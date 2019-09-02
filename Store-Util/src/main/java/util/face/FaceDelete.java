package util.face;

import util.AuthUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 删除人脸
 */
public class FaceDelete {

    public static String delete(String phone,String token) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/face/delete";
        try {
            Map<String, Object> map = new HashMap<>();

            map.put("user_id", phone);
            map.put("group_id", "login");
            map.put("face_token", token);

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthUtil.access_token;

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
