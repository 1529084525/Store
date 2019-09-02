package com.xzx.hanfu.store_controller.controller.noticeType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.noticeType.NoticeTypeService;

@RestController
@RequestMapping(value = "NoticeType", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api
public class NoticeTypeController {

    @Autowired
    private NoticeTypeService noticeTypeService;

    @ApiOperation(notes = "查询公告类型", value = "查询公告类型")
    @GetMapping("selectAll")
    public Object selectAll(){
        return noticeTypeService.selectAll();
    }
}
