package com.xzx.hanfu.store_controller.controller.api;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.UrlUtil;
import util.VeriflyAuthCodeUtil;
import util.expressage.AliExpressageUtil;
import util.face.HttpUtil;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "Api", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @ApiOperation(value = "验证滑块")
    @GetMapping("VeriflySliding")
    public Object veriflySliding(String ticket, String randstr) {
        return VeriflyAuthCodeUtil.Verifly(ticket, randstr);
    }

    @ApiOperation("查询快递")
    @GetMapping("FindExpressage")
    public Object findExpressage(String expressageCode) {
        String s = stringRedisTemplate.opsForValue().get("expreessage_" + expressageCode);
        if (s != null) {
            return s;
        } else {
            String expressage = AliExpressageUtil.searchExpressage(expressageCode);
            stringRedisTemplate.opsForValue().set("expreessage_" + expressageCode, expressage, 3, TimeUnit.HOURS);
            return expressage;
        }
    }

    @ApiOperation("获取天气")
    @GetMapping("Weather")
    public Object weather(){
        return UrlUtil.sendGet("http://t.weather.sojson.com/api/weather/city/101220101",null);
    }
}
