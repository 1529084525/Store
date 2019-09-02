package com.xzx.hanfu.store_controller.controller.carousel;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.Carousel;
import service.carousel.CarouselService;

import java.util.List;

@RestController
@RequestMapping(value = "Carouse", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api
public class CarouselController {
    @Autowired
    private CarouselService carouselService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @ApiOperation(notes = "用来查询所有轮播图", value = "查询轮播图")
    @GetMapping("selectAllCarouselStatus")
    public Object selectAllCarouselStatus(){
        try{
            String carsousel = stringRedisTemplate.opsForValue().get("carousel");
            if(carsousel==null){
                List<Carousel> list=carouselService.selectAll();
                stringRedisTemplate.opsForValue().set("carousel", JSON.toJSONString(list));
                return list;
            }else{
                return carsousel;
            }
        }catch (Exception e){
            List<Carousel> list=carouselService.selectAll();
            stringRedisTemplate.opsForValue().set("carousel", JSON.toJSONString(list));
            return list;
        }
    }
}
