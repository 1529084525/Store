package com.xzx.hanfu.store_controller.controller.paymentCode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.PaymentCode;
import service.paymentCode.PaymentCodeService;
import util.GetPhoneUtil;
import util.MD5Util;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "PaymentCode", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api
public class PaymentCodeController {

    @Autowired
    private PaymentCodeService paymentCodeService;

    @ApiOperation("添加支付密码")
    @PostMapping("Insert")
    public Object insert(PaymentCode paymentCode, HttpServletRequest request) throws Exception {
        String phone = GetPhoneUtil.getPhone(request);
        if (paymentCodeService.checkExist(phone) > 0)
            return -1;

        paymentCode.setPaymentPassword(MD5Util.md5(paymentCode.getPaymentPassword(), MD5Util.KEY));
        paymentCode.setUserPhone(phone);
        return paymentCodeService.insertPayment(paymentCode);
    }

    @ApiOperation("判断是否存在支付密码")
    @GetMapping("CheckTrue")
    public Object checkTrue(HttpServletRequest request) throws Exception {
        return paymentCodeService.checkExist(GetPhoneUtil.getPhone(request));
    }

    @ApiOperation("修改支付密码")
    @PostMapping("Update")
    public Object update(PaymentCode paymentCode, HttpServletRequest request) throws Exception {
        paymentCodeService.deleteByPhone(GetPhoneUtil.getPhone(request));
        return insert(paymentCode, request);
    }

    @ApiOperation("查看密码是否一致")
    @PostMapping("CheckEquality")
    public Object checkEquality(String pwd, HttpServletRequest request) throws Exception {
        String phone = GetPhoneUtil.getPhone(request);
        if (MD5Util.verify(pwd, MD5Util.KEY, paymentCodeService.selectByPhone(phone).getPaymentPassword()))
            return 1;
        else
            return -1;
    }

}
