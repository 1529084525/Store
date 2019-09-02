package com.xzx.hanfu.store_controller.controller.integral;

import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.integral.IntegralService;
import service.user.UserService;
import util.GetPhoneUtil;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "Integral", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api
public class IntegralController {

    @Autowired
    private IntegralService integralService;
    @Autowired
    private UserService userService;

    @ApiOperation("查询用户的积分信息")
    @GetMapping("FindIntegral")
    public Object findIntegral(HttpServletRequest request)
            throws Exception {
        try {
            String phone = GetPhoneUtil.getPhone(request);
            return ImmutableMap.of("integral", integralService.selectByPhone(phone),
                    "photo", userService.selectPhotoByPhone(phone)
            );
        } catch (Exception e) {
            return ImmutableMap.of("result", "no");
        }
    }

}
