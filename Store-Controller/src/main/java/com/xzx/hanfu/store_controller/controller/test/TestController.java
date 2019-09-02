package com.xzx.hanfu.store_controller.controller.test;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.commodity.CommodityService;

@RestController
@RequestMapping(value = "Test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api
public class TestController {

    @Autowired
    private CommodityService commodityService;

    @GetMapping("GetTree")
    public Object getTree() {
        return commodityService.selectAllByTree("");
    }

}
