package com.xzx.hanfu.store_controller.controller.face;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.Face;
import pojo.MerchandiseSKU;
import pojo.ShoppingCar;
import service.face.FaceService;
import service.merchandiseSKU.MerchandiseSKUService;
import service.shoppingCar.ShoppingCarService;
import util.ConvertBase64Util;
import util.ConvertFileUtil;
import util.GetPhoneUtil;
import util.TokenUtil;
import util.face.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "Face", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api
public class FaceController {

    @Autowired
    private FaceService faceService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ShoppingCarService shoppingCarService;  //
    @Autowired
    private MerchandiseSKUService merchandiseSKUService;

    @ApiOperation("查找自己的人脸")
    @GetMapping("FindFace")
    public Object findFace(HttpServletRequest request) throws Exception {
        return faceService.selectFaceByPhone(GetPhoneUtil.getPhone(request));
    }

    @ApiOperation("删除自己的人脸")
    @GetMapping("DelFace")
    public Object delFace(HttpServletRequest request) throws Exception {
        String phone = GetPhoneUtil.getPhone(request);
        //删除人脸库
        FaceDelete.delete(phone, faceService.selectFaceByPhone(phone).getFaceToken());
        return faceService.deleteByPhone(phone);
    }

    @ApiOperation("添加人脸")
    @PostMapping("AddFace")
    public Object addFace(String base64, HttpServletRequest request) throws Exception {
        String phone = GetPhoneUtil.getPhone(request);
        //获取base64 转换 MultipartFile 转换 File
        String resultJSON = FaceAdd.addByBase(phone, ConvertFileUtil.getFile(request, Objects.requireNonNull(ConvertBase64Util.base64ToMultipart(base64))));
        JSONObject jsonObject = JSON.parseObject(resultJSON);
        System.out.println(jsonObject);
        System.out.println(jsonObject.get("error_code"));
        if (Integer.parseInt(jsonObject.get("error_code").toString()) == 0) {
            //调用成功!
            Object face_token = JSON.parseObject(jsonObject.get("result").toString()).get("face_token");
            Face face = new Face();
            face.setUserPhone(phone);
            face.setFaceToken(face_token.toString());
            int i = faceService.insertFace(face);
            if (i > 0)
                return ImmutableMap.of("result", "ok");
            else
                return ImmutableMap.of("result", "no");
        } else {
            return ImmutableMap.of("result", "no");
        }
    }

    @ApiOperation("检测活体")
    @PostMapping("verifyFace")
    public Object VerifyFace(String base64, HttpServletRequest request) throws Exception {
        return FaceVerify.faceVerify(ConvertFileUtil.getFile(request, Objects.requireNonNull(ConvertBase64Util.base64ToMultipart(base64))));
    }

    @ApiOperation("人脸检测")
    @PostMapping("DetectFace")
    public Object detectFace(String base64, HttpServletRequest request)
            throws Exception {
        return FaceDetect.detect(ConvertFileUtil.getFile(request, Objects.requireNonNull(ConvertBase64Util.base64ToMultipart(base64))));
    }

    @ApiOperation("真实身份验证")
    @PostMapping("VerifyPerson")
    public Object verifyPerson(String base64, HttpServletRequest request, String name, String code)
            throws Exception {
        return PersonVerify.personverify(ConvertFileUtil.getFile(request, Objects.requireNonNull(ConvertBase64Util.base64ToMultipart(base64))), name, code);
    }

    @ApiOperation("人脸登陆")
    @PostMapping("LoginFace")
    public Object loginFace(String base64, HttpServletRequest request) throws Exception {
        JSONObject jsonObject = JSON.parseObject(FaceSearch.search(ConvertFileUtil.getFile(request, Objects.requireNonNull(ConvertBase64Util.base64ToMultipart(base64)))));
        if (Integer.parseInt(jsonObject.get("error_code").toString()) == 0) {
            JSONObject json = JSON.parseObject(JSON.parseArray(JSON.parseObject(jsonObject.get("result").toString()).get("user_list").toString()).get(0).toString());
            if (Double.parseDouble(json.get("score").toString()) > 90) {
                Object phone = json.get("user_id");
                long time = 1000 * 60 * 30;//30分钟
                Map<String, Object> map = new HashMap<String, Object>() {{
                    put("phone", phone);
                }};
                String jwt = TokenUtil.createJWT(map, time);
                stringRedisTemplate.opsForValue().set(phone + "_token", jwt, time, TimeUnit.MILLISECONDS);
                map.put("token", jwt);
                map.put("msg", "ok");
                //获取购物车信息
                String so = request.getHeader("carToken");
                List<ShoppingCar> cars = shoppingCarService.selectAll(so,1);
                for (int i = 0; i < cars.size(); i++) {
                    MerchandiseSKU merchandiseSKU = merchandiseSKUService.selectByMerchandise(cars.get(i).getMerchandiseId(),
                            cars.get(i).getMerchandiseColor(), cars.get(i).getMerchandiseSize());
                    cars.get(i).setUserPhone(phone.toString());
                    ShoppingCar car = shoppingCarService.checkExist(cars.get(i), 0);
                    if (car == null)
                        shoppingCarService.insertCar(cars.get(i));
                    else{
                        if ((car.getMerchandiseCount() + cars.get(i).getMerchandiseCount()) > merchandiseSKU.getMerchandiseCount()) {
                            break;
                        }
                        car.setMerchandiseCount(car.getMerchandiseCount() + cars.get(i).getMerchandiseCount());
                        car.setMerchandiseMoney(car.getMerchandiseMoney() + cars.get(i).getMerchandiseMoney());
                        shoppingCarService.updateCar(car, 0);
                    }
                }
                shoppingCarService.deleteAll(so, 1);
                return map;
            } else
                return ImmutableMap.of("msg", "no");
        } else {
            return ImmutableMap.of("msg", "no");
        }
    }

}
