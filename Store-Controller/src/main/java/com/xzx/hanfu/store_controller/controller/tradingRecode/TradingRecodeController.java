package com.xzx.hanfu.store_controller.controller.tradingRecode;

import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.TradingRecode;
import service.tradingRecode.TradingRecodeService;
import util.GetPhoneUtil;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "TradingRecode", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api
public class TradingRecodeController {
    @Autowired
    private TradingRecodeService tradingRecodeService;

    @ApiOperation("添加信息")
    @GetMapping("InsertRecode")
    public Object insertRecode(TradingRecode tradingRecode, HttpServletRequest request) {
        return null;
    }

    @ApiOperation("查询我的消费记录")
    @GetMapping("FindMyRecode")
    public Object findMyRecode(HttpServletRequest request,int page) throws Exception {
        String phone = GetPhoneUtil.getPhone(request);
        return ImmutableMap.of(
                "count", tradingRecodeService.selectCountByPhone(phone),
                "data",tradingRecodeService.selectAllByPhone(phone,(page - 1) * 20)
        );
    }
}
