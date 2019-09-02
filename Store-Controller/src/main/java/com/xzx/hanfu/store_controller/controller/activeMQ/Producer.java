package com.xzx.hanfu.store_controller.controller.activeMQ;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.webSocket.WebSocketServer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "Producer", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api
public class Producer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;



    @RequestMapping("/sendMsg")
    public void sendMsg(String msg) {
        jmsMessagingTemplate.convertAndSend("my_msg", msg);
        System.out.println("msg发送成功");
    }

    @RequestMapping("/sendMap")
    public void sendMap() {
        Map map = new HashMap();
        map.put("mobile", "13888888888");
        map.put("content", "王总喜提兰博基尼");
        jmsMessagingTemplate.convertAndSend("my_map", map);
        System.out.println("map发送成功");
    }

    @RequestMapping("/send")
    public void send(String text) throws IOException {
        WebSocketServer.sendInfo(text);
        System.out.println("成功");
    }
}
