package com.nanfenggongxiang.Controller.Front;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by skyisbule on 2018/4/12.
 * 控制首页飘过的那行公告
 */
@RequestMapping("/front/notice")
@RestController
public class frontNoticeController {

    private String content = "欢迎来到南风~~~~~~";

    @RequestMapping("/set")
    public String set(String content){
        this.content = content;
        return "success";
    }

    @RequestMapping("/get")
    public String getContent(){
        return this.content;
    }

}
