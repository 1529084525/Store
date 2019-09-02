package com.xzx.hanfu.store_controller.controller.address;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.Address;
import service.address.AddressService;
import util.GetPhoneUtil;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "Address", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api
public class AddressController {

    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "获取用户地址", notes = "根据用户默认地址排序")
    @GetMapping("FindAddress")
    public Object findAddress(HttpServletRequest request) throws Exception {
        return addressService.selectAddressByPhone(GetPhoneUtil.getPhone(request));
    }

    @ApiOperation("添加地址")
    @GetMapping("Add")
    public Object add(Address address, HttpServletRequest request) throws Exception {
        address.setUserPhone(GetPhoneUtil.getPhone(request));
        return addressService.insertAddress(address);
    }

    @ApiOperation(value = "查询单条地址信息", notes = "根据id查询")
    @GetMapping("FindOne/{id}")
    public Object findOne(@PathVariable int id) {
        return addressService.selectAddressById(id);
    }

    @ApiOperation("修改地址信息")
    @GetMapping("Update")
    public Object update(Address address) {
        return addressService.updateById(address);
    }

    @ApiOperation("删除地址")
    @GetMapping("Delete/{id}")
    public Object delete(@PathVariable int id) {
        return addressService.deleteById(id);
    }

    @ApiOperation("修改默认地址")
    @GetMapping("UpdateDefault/{id}")
    public Object updateDefault(@PathVariable int id, HttpServletRequest request) throws Exception {
        return addressService.updateDefault(id, GetPhoneUtil.getPhone(request));
    }
}
