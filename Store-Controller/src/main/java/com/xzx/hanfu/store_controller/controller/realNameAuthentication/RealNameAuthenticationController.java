package com.xzx.hanfu.store_controller.controller.realNameAuthentication;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.RealNameAuthentication;
import service.realNameAuthentication.RealNameAuthenticationService;
import util.GetPhoneUtil;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "RealNameAuthentication", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api
public class RealNameAuthenticationController {

    @Autowired
    private RealNameAuthenticationService realNameAuthenticationService;

    @ApiOperation("判断是否存在")
    @PostMapping("CheckExist")
    public Object checkExist(String code) {
        return realNameAuthenticationService.checkExist(code);
    }

    @ApiOperation("添加实名认证")
    @PostMapping("InsertAuth")
    public Object insertAuth(RealNameAuthentication realNameAuthentication, HttpServletRequest request) throws Exception {
        String phone = GetPhoneUtil.getPhone(request);
        //避免恶意攻击
        if (realNameAuthenticationService.selectOne(phone) != null)
            return -1;

        realNameAuthentication.setUserPhone(phone);
        return realNameAuthenticationService.insertAuthentication(realNameAuthentication);
    }

    @ApiOperation("查询实名认证")
    @GetMapping("FindAuth")
    public Object findAuth(HttpServletRequest request) throws Exception {
        return realNameAuthenticationService.selectOne(GetPhoneUtil.getPhone(request));
    }
}
