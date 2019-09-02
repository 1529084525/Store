package com.xzx.hanfu.store_controller.controller.commodity;

import com.alibaba.fastjson.JSON;
import customPojo.CommodityJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.commodity.CommodityService;

import java.util.List;

@RestController
@RequestMapping(value = "Commodity", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api
public class CommodityController {

    @Autowired
    private CommodityService commodityService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @ApiOperation(notes = "用来获取分类json 用户三级联动", value = "获取分类")
    @GetMapping("selectAll")
    public Object selectAll(String commodityId) {
        return commodityService.selectAll(commodityId);
    }

    @ApiOperation(notes = "用来获取分类json", value = "获取分类")
    @GetMapping("GetJson")
    public Object getJson() {
        //根据key获取缓存中的val
        try {
            String commodity = stringRedisTemplate.opsForValue().get("commodity");
            if (commodity == null) {
                List<CommodityJson> list = commodityService.selectAllByJson("");
                stringRedisTemplate.opsForValue().set("commodity", JSON.toJSONString(list));
                return list;
            } else {
                return commodity;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return commodityService.selectAllByJson("");
        }
    }

    @ApiOperation("获取大分类")
    @GetMapping("GetOne")
    public Object getOne() {
        return commodityService.selectOne();
    }

    @ApiOperation("获取随机分类")
    @GetMapping("GetRand")
    public Object getRand(String one) {
        return commodityService.selectByOne(one);
    }
}
