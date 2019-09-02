package com.xzx.hanfu.store_controller.controller.brand;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.brand.BrandService;


@RestController
@RequestMapping(value = "Brand", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api
public class BrandController {

    @Autowired
    private BrandService brandService;

    @ApiOperation(notes = "查询所有品牌信息", value = "查询所有品牌信息")
    @GetMapping("selectAll")
    public Object selectAll() {
        return brandService.selectAll();
    }

    @ApiOperation("根据数量查询品牌")
    @GetMapping("FindAll")
    public Object findAll() {
        return brandService.selectAllByDesc();
    }
}
