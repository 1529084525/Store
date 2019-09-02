package com.xzx.hanfu.store_controller.controller.activeMQ;

import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/*@RestController
@RequestMapping(value = "Consumer", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api*/
@Component
public class Consumer {

    @JmsListener(destination = "my_msg")
    public void readMsg(String text) {
        System.out.println("接收到消息：" + text);
    }

    @JmsListener(destination = "my_map")
    public void readMap(Map map) {
        System.out.println(map);
    }

}
