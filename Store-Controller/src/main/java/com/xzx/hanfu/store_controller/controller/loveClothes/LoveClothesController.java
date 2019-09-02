package com.xzx.hanfu.store_controller.controller.loveClothes;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.loveClothes.LoveClothesServices;

@RestController
@RequestMapping(value = "LoveClothes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api
public class LoveClothesController {

    @Autowired
    private LoveClothesServices loveClothesServices;

    @ApiOperation(notes = "收藏商品", value = "收藏商品")
    @GetMapping("insertLove")
    public Object insertLove(String userPhone,String merchandiseId){
        int result=loveClothesServices.insertLove(userPhone,merchandiseId);
        if(result>0){
            return "ok";
        }else {
            return "no";
        }
    }

    @ApiOperation(notes = "根据id删除收藏信息", value = "根据id删除收藏信息")
    @GetMapping("deleteLoveById")
    public Object deleteLoveById(String userPhone,String merchandiseId){
        int result=loveClothesServices.deleteLoveById(userPhone,merchandiseId);
        if(result>0){
            return "ok";
        }else {
            return "no";
        }
    }

    @ApiOperation(notes = "根据id删除收藏信息", value = "根据id删除收藏信息")
    @GetMapping("selectLove")
    public Object selectLove(String userPhone,String merchandiseId){
        return loveClothesServices.selectLove(userPhone,merchandiseId);
    }
}
