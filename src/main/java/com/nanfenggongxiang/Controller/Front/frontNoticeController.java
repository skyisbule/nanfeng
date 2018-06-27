package com.nanfenggongxiang.Controller.Front;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by skyisbule on 2018/4/12.
 * 控制首页飘过的那行公告
 */
@Api("控制首页的公告")
@RequestMapping("/front/notice")
@RestController
public class frontNoticeController {

    private String content = "欢迎来到南风~~~~~~";

    @ApiOperation("设置那行字的内容，字符串即可，接口无限制。")
    @RequestMapping("/set")
    public String set(String content){
        this.content = content;
        return "success";
    }

    @ApiOperation("拿到那行字的内容")
    @RequestMapping("/get")
    public String getContent(){
        return this.content;
    }

}
