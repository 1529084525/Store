package util.face;


import util.AuthUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 身份验证
 */
public class PersonVerify {

    public static String personverify(File
                                              file, String name, String code) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/person/verify";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", Base64Util.encode(FileUtil.readFileByBytes(file)));
            map.put("image_type", "BASE64");
            map.put("id_card_number", code);
            map.put("liveness_control", "NORMAL");
            map.put("name", name);
            map.put("quality_control", "NONE");

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