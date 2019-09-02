package com.xzx.hanfu.store_controller.controller.activeMQ;

import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "test2", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api
public class test {

    @JmsListener(destination = "my_msg")
    @GetMapping("tt")
    public Object tt(String text) {
        return text;
    }

}
