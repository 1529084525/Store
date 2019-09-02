package com.xzx.hanfu.store_controller.controller.merchandiseSKU;

import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.MerchandiseSKU;
import service.merchandiseSKU.MerchandiseSKUService;

@RestController
@RequestMapping(value = "MerchandiseSKU", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api
public class MerchandiseSKUController {

    @Autowired
    private MerchandiseSKUService merchandiseSKUService;

    @ApiOperation(notes = "添加商品详细+信息", value = "添加商品详细+信息")
    @GetMapping("insertInfo")
    public Object insertInfo(MerchandiseSKU merchandiseSKU) {
        int result = merchandiseSKUService.insertInfo(merchandiseSKU);
        if (result > 0) {
            return "ok";
        } else {
            return "no";
        }
    }

    @ApiOperation("获取尺码颜色等")
    @GetMapping("FindDetails")
    public Object findDetails(String merchandise) {
        return ImmutableMap.of("size", merchandiseSKUService.selectSizeById(merchandise),
                "color", merchandiseSKUService.selectColorById(merchandise)
        );
    }

    @ApiOperation("获取总库存")
    @GetMapping("GetRepertory")
    public Object getRepertory(String merchandise) {
        return merchandiseSKUService.selectCountById(merchandise);
    }

    @ApiOperation("查询指定的库存")
    @GetMapping("GetOneRepertory")
    public Object getOneRepertory(String merchandiseId, String color, String size) {
        return merchandiseSKUService.selectRepertoryByMerchandise(merchandiseId, color, size);
    }

    @ApiOperation("根据商品编号+选择颜色查询具体价格")
    @GetMapping("selectMoneyByColor")
    public Object selectMoneyByColor(String merchandiseId,String merchandiseColor){
        return merchandiseSKUService.selectMoneyByColor(merchandiseId,merchandiseColor);
    }
}
