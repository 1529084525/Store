package util.face;

import util.AuthUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 人脸注册
 */
public class FaceAdd {

    /**
     * 根据base64上传人脸
     *
     * @param phone
     * @param file
     * @return
     */
    public static String addByBase(String phone, File file) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", Base64Util.encode(FileUtil.readFileByBytes(file)));
            map.put("group_id", "login");//用户组id
            map.put("user_id", phone);//用户id
            map.put("liveness_control", "NONE");//活体检测控制
            map.put("image_type", "BASE64");//图片类型
            map.put("quality_control", "NONE");//图片质量控制

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

    /**
     * 根据网络图片上传人脸
     *
     * @param phone
     * @param url
     * @return
     */
    public static String addByUrl(String phone, String urlImage) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", urlImage);
            map.put("group_id", "login");//用户组id
            map.put("user_id", phone);//用户id
            map.put("liveness_control", "NONE");//活体检测控制
            map.put("image_type", "URL");//图片类型
            map.put("quality_control", "NONE");//图片质量控制

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
