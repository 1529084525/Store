package com.xzx.hanfu.store_controller.controller.notice;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.Notice;
import service.notice.NoticeService;

import java.util.List;


@RestController
@RequestMapping(value = "Notice", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api
public class NoticeController {

    @Autowired
    private NoticeService noticeService;


    @ApiOperation(notes = "添加公告", value = "添加公告")
    @GetMapping("insertNotice")
    public Object insertNotice(Notice notice){
        int result=noticeService.insertNotice(notice);
        if(result>0){
            return "ok";
        }else{
            return "no";
        }
    }

    @ApiOperation(notes = "查询公告", value = "查询公告")
    @GetMapping("selectAll")
    public Object selectAll(){
        return noticeService.selectAll();
    }

    @ApiOperation(notes = "根据noticeId查询公告详情", value = "根据noticeId查询公告详情")
    @GetMapping("selectInfoById")
    public Object selectInfoById(int noticeId){
        return noticeService.selectInfoById(noticeId);
    }

    @ApiOperation(notes = "查询公告总数量", value = "查询公告总数量")
    @GetMapping("selectCount")
    public Object selectCount(){
        return noticeService.selectCount();
    }

    @ApiOperation(notes = "分页查询公告", value = "分页查询公告")
    @GetMapping("selectPage")
    public Object selectPage(Integer start,Integer count){
        return noticeService.selectPage(start,count);
    }

    @ApiOperation(notes = "查询最新的一条公告", value = "查询最新的一条公告")
    @GetMapping("selectNew")
    public Object selectNew(){
        return noticeService.selectNew();
    }

    @ApiOperation(notes = "点击公告浏览量+1", value = "点击公告浏览量+1")
    @GetMapping("updateHotCount")
    public Object updateHotCount(int noticeId){
        int result=noticeService.updateHotCount(noticeId);
        if(result>0){
            return "ok";
        }else {
            return "no";
        }
    }
}
