package com.xzx.hanfu.store_controller.controller.merchandise;

import com.google.common.collect.ImmutableMap;
import customPojo.GoPay;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javafx.beans.property.adapter.ReadOnlyJavaBeanBooleanProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.Merchandise;
import service.merchandise.MerchandiseService;

import java.sql.DataTruncation;

@RestController
@RequestMapping(value = "Merchandise", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api
public class MerchandiseController {
    @Autowired
    private MerchandiseService merchandiseService;

    private GoPay goPay = new GoPay();

    @ApiOperation(notes = "添加商品信息", value = "添加商品信息")
    @GetMapping("insertMerchandise")
    public Object insertMerchandise(Merchandise merchandise) {
        int result = merchandiseService.insertMerchandise(merchandise);
        System.out.println(merchandise);
        if (result > 0) {
            return "ok";
        } else {
            return "no";
        }
    }

    @ApiOperation(notes = "在发布商品时查询改商品编号是否存在", value = "在发布商品时查询改商品编号是否存在")
    @GetMapping("selectId")
    public Object selectId(String merchandiseId) {
        int result = merchandiseService.selectId(merchandiseId);
        if (result == 1) {//等于1 则是存在 返回no
            return "no";
        } else {
            return "ok";
        }
    }

    @ApiOperation("查询爆款商品")
    @GetMapping("FindFaddish/{limit}")
    public Object findFaddish(@PathVariable int limit) {
        return merchandiseService.selectFaddish(limit);
    }

    @ApiOperation("查询分类商品展示到页面")
    @GetMapping("FindByCommodityId")
    public Object findByCommodityId(String commodityId) {
        return merchandiseService.selectByIndex(commodityId);
    }

    @ApiOperation("分页查询随机商品数据到页面hot_page.html")
    @GetMapping("selectHot")
    public Object selectHot(int start, int count) {
        return merchandiseService.selectHot(start, count);
    }

    @ApiOperation("查询商品总数")
    @GetMapping("selectMerchandiseCount")
    public Object selectMerchandiseCount() {
        return merchandiseService.selectMerchandiseCount();
    }

    @ApiOperation("分页+销量最高查询商品数据")
    @GetMapping("selectHeightSellCount")
    public Object selectHeightSellCount(int start, int count) {
        return merchandiseService.selectHeightSellCount(start, count);
    }

    @ApiOperation("分页+一口价最低 查询商品数据")
    @GetMapping("selectLowPrice")
    public Object selectLowPrice(int start, int count) {
        return merchandiseService.selectLowPrice(start, count);
    }

    @ApiOperation("立即支付---塞值")
    @GetMapping("goPay")
    public Object goPay(String merchandiseId, String merchandiseTitle, int merchandisePresentIntegral,
                        int merchandiseFreight, String merchandiseColor, String merchandiseImg, String merchandiseSize
            , int merchandiseCount, Double merchandiseMoney) {
        goPay.setMerchandiseId(merchandiseId);
        goPay.setMerchandiseTitle(merchandiseTitle);
        goPay.setMerchandisePresentIntegral(merchandisePresentIntegral);
        goPay.setMerchandiseFreight(merchandiseFreight);
        goPay.setMerchandiseColor(merchandiseColor);
        goPay.setMerchandiseImg(merchandiseImg);
        goPay.setMerchandiseSize(merchandiseSize);
        goPay.setMerchandiseCount(merchandiseCount);
        goPay.setMerchandiseMoney(merchandiseMoney);
        return goPay;
    }


    @ApiOperation("查询")
    @GetMapping("Search")
    public Object search(String merchandiseCommodity, String merchandiseTitle,
                         String merchandiseBrandId, String merchandiseColor, String merchandiseSize, int page) {
        return ImmutableMap.of("count", merchandiseService.selectBySearchCount(
                merchandiseCommodity, merchandiseTitle, merchandiseBrandId, merchandiseColor, merchandiseSize).size(),
                "data", merchandiseService.selectBySearch(merchandiseCommodity, merchandiseTitle,
                        merchandiseBrandId, merchandiseColor, merchandiseSize, ((page - 1) * 20)));
    }

}
