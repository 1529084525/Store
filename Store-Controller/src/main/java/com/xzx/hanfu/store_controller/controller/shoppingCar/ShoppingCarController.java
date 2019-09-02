package com.xzx.hanfu.store_controller.controller.shoppingCar;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.MerchandiseSKU;
import pojo.ShoppingCar;
import service.merchandiseSKU.MerchandiseSKUService;
import service.shoppingCar.ShoppingCarService;
import util.FormattingDoubleUtil;
import util.GetPhoneUtil;
import util.TokenUtil;
import util.system.SystemUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "ShoppingCar", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api
public class ShoppingCarController {

    @Autowired
    private ShoppingCarService shoppingCarService;
    @Autowired
    private MerchandiseSKUService merchandiseSKUService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @ApiOperation("添加购物车")
    @GetMapping("InsertCar")
    public Object insertCar(ShoppingCar shoppingCar, HttpServletRequest request) {
        MerchandiseSKU merchandiseSKU = merchandiseSKUService.selectByMerchandise(shoppingCar.getMerchandiseId(),
                shoppingCar.getMerchandiseColor(), shoppingCar.getMerchandiseSize());
        //判断数量是否大于总数量(再次验证)
        if (shoppingCar.getMerchandiseCount() > merchandiseSKU.getMerchandiseCount())
            return ImmutableMap.of("result", "no", "msg", "数量不正确");
        //计算金额
        shoppingCar.setMerchandiseMoney(
                FormattingDoubleUtil.parseDouble(merchandiseSKU.getMerchandiseMoney()
                        * shoppingCar.getMerchandiseCount()));
        try {
            String phone = GetPhoneUtil.getPhone(request);
            shoppingCar.setUserPhone(phone);

            //最新数据
            ShoppingCar car = shoppingCarService.checkExist(shoppingCar, 0);
            if (car == null)
                return ImmutableMap.of("data", shoppingCarService.insertCar(shoppingCar),
                        "msg", "登陆添加");
            else {
                if ((car.getMerchandiseCount() + shoppingCar.getMerchandiseCount()) > merchandiseSKU.getMerchandiseCount()) {
                    return ImmutableMap.of("data", -1,
                            "msg", "数值错误");
                }
                car.setMerchandiseCount(car.getMerchandiseCount() + shoppingCar.getMerchandiseCount());
                car.setMerchandiseMoney(car.getMerchandiseMoney() + shoppingCar.getMerchandiseMoney());
                return ImmutableMap.of("data", shoppingCarService.updateCar(car, 0),
                        "msg", "修改添加");
            }
            //以登录
        } catch (Exception e) {
            //未登录  游客
            String mac = request.getHeader("carToken");
          /*  List<ShoppingCar> list;   redis
            String json = stringRedisTemplate.opsForValue().get(mac);
            if (json != null) {
                list = JSON.parseObject(json, new TypeReference<List<ShoppingCar>>() {
                });
            } else {
                list = new ArrayList<>();
            }
            list.add(shoppingCar);
            stringRedisTemplate.opsForValue().set(mac,
                    JSON.toJSONString(list));
            return ImmutableMap.of("data", list.size(),
                    "msg", "未登录添加");*/
            shoppingCar.setUserPhone(mac);
            //最新数据
            ShoppingCar car = shoppingCarService.checkExist(shoppingCar, 1);
            if (car == null)
                return ImmutableMap.of("data", shoppingCarService.insertByLogin(shoppingCar),
                        "msg", "未登录添加");
            else {
                if ((car.getMerchandiseCount() + shoppingCar.getMerchandiseCount()) > merchandiseSKU.getMerchandiseCount()) {
                    return ImmutableMap.of("data", -1,
                            "msg", "数值错误");
                }
                car.setMerchandiseCount(car.getMerchandiseCount() + shoppingCar.getMerchandiseCount());
                car.setMerchandiseMoney(car.getMerchandiseMoney() + shoppingCar.getMerchandiseMoney());
                return ImmutableMap.of("data", shoppingCarService.updateCar(car, 1),
                        "msg", "修改添加");
            }

        }
    }

    @ApiOperation("查询购物车数量")
    @GetMapping("FindCount")
    public Object findCount(HttpServletRequest request) throws Exception {
        try {
            String phone = GetPhoneUtil.getPhone(request);
            System.out.println("进入第一次");
            return ImmutableMap.of("data", shoppingCarService.selectCountByPhone(phone, 0),
                    "jwt", "");
        } catch (Exception e) {
            /*JSONArray objects = JSON.parseArray(stringRedisTemplate.opsForValue().get(SystemUtil.getRequestBrowserInfo(request) + "_" +
                    SystemUtil.getMacAddress(SystemUtil.getIpAddr(request))));
            return objects == null ? 0 : objects.size();  redis*/
            String jwt = request.getHeader("carToken");
            if (jwt == null) {
                jwt = TokenUtil.createJWT(new HashMap<>(), 60000 * 60 * 24 * 30L);
            }
            return ImmutableMap.of("data", shoppingCarService.selectCountByPhone(jwt, 1),
                    "jwt", jwt);
        }
    }

    @ApiOperation("查询购物车数据")
    @GetMapping("FindAll")
    public Object findAll(HttpServletRequest request) {
        try {
            String phone = GetPhoneUtil.getPhone(request);
            return shoppingCarService.selectAllByPhone(phone, 0);
        } catch (Exception e) {
           /* List<ShoppingCar> list = JSON.parseObject(stringRedisTemplate.opsForValue().get(SystemUtil.getRequestBrowserInfo(request) + "_" +
                    SystemUtil.getMacAddress(SystemUtil.getIpAddr(request))), new TypeReference<List<ShoppingCar>>() {
            });
            System.out.println(list.size());
            return shoppingCarService.selectAllByNoLogin(list); redis*/
            return shoppingCarService.selectAllByPhone(request.getHeader("carToken"), 1);
        }
    }

    @ApiOperation("修改数量")
    @GetMapping("UpdateCount/{type}")
    public Object updateCount(int shoppingId, int count, @PathVariable int type) {
        ShoppingCar shoppingCar = shoppingCarService.selectById(shoppingId);//自己的购物车信息
        MerchandiseSKU merchandiseSKU = merchandiseSKUService.selectByShoppingCar(shoppingCar);//官方
        int counts = shoppingCar.getMerchandiseCount();
        if (type == 0) counts = count;
        else counts -= count;
        if (counts > merchandiseSKU.getMerchandiseCount())//判断是否满了
            return ImmutableMap.of("code", "1", "msg", "数量不正确", "count", merchandiseSKU.getMerchandiseCount());
        double money = merchandiseSKU.getMerchandiseMoney() * count;
        shoppingCar.setMerchandiseMoney(money);
        shoppingCar.setMerchandiseCount(count);
        //很多业务逻辑
        return ImmutableMap.of("result", shoppingCarService.updateCar(shoppingCar, 0),
                "code", 0,
                "msg", "修改成功",
                "data", new HashMap<String, Object>() {{
                    put("money", money);
                    put("count", count);
                }});
    }

}
