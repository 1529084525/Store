package com.xzx.hanfu.store_controller.controller.merchandiseDetails;

import com.google.common.collect.ImmutableMap;
import customPojo.Merchandise_Details;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.MerchandiseDetails;
import service.brand.BrandService;
import service.merchandiseDetails.MerchandiseDetailsService;

@RestController
@RequestMapping(value = "MerchandiseDetails", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api
public class MerchandiseDetailsController {
    @Autowired
    private MerchandiseDetailsService merchandiseDetailsService;
    @Autowired
    private BrandService brandService;

    @ApiOperation(notes = "添加商品详细信息", value = "添加商品详细信息")
    @GetMapping("insertInfo")
    public Object insertInfo(MerchandiseDetails merchandiseDetails) {
        int result = merchandiseDetailsService.insertInfo(merchandiseDetails);
        if (result > 0) {
            return "ok";
        } else {
            return "no";
        }
    }

    @ApiOperation("查询商品详情信息")
    @GetMapping("FindDetails")
    public Object findDetails(String merchandise) {
        Merchandise_Details merchandise_details = merchandiseDetailsService.selectMerchandise_DetailsById(merchandise);
        return ImmutableMap.of("data", merchandise_details,
                "brand", brandService.selectById(merchandise_details.getMerchandise().getMerchandiseBrandId()));
    }
}
