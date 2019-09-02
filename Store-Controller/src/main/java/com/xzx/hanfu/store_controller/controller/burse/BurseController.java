package com.xzx.hanfu.store_controller.controller.burse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.TradingRecode;
import service.burse.BurseService;
import service.tradingRecode.TradingRecodeService;
import util.FormattingDoubleUtil;
import util.GetPhoneUtil;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "Burse", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api
public class BurseController {

    @Autowired
    private BurseService burseService;
    @Autowired
    private TradingRecodeService tradingRecodeService;

    @ApiOperation("获取用户钱包余额")
    @PostMapping("FindMyBurse")
    public Object findMyBurse(HttpServletRequest request) throws Exception {
        return burseService.selectByPhone(GetPhoneUtil.getPhone(request));
    }

    @ApiOperation("充值金额")
    @GetMapping("AddBurse")
    public Object addBurse(HttpServletRequest request, TradingRecode tradingRecode) throws Exception {
        String phone = GetPhoneUtil.getPhone(request);
        burseService.operationBalance(phone, FormattingDoubleUtil.parseDouble(tradingRecode.getTradingRecodeMoney()), 0);
        tradingRecode.setUserPhone(phone);
        tradingRecode.setStatusId(1);//已完成2
        return tradingRecodeService.insertRecode(tradingRecode);
    }
}
