package com.xzx.hanfu.store_controller.controller.token;

import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "Token", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api
public class TokenController {

    @ApiOperation("进入拦截器验证")
    @GetMapping("Check")
    public Object check(HttpServletRequest request) {
        return ImmutableMap.of("code", "200");
    }

}
