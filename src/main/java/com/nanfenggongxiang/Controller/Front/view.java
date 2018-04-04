package com.nanfenggongxiang.Controller.Front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by skyisbule on 2018/4/4.
 */
@Controller
@RequestMapping(value = "/",method = RequestMethod.GET)
public class view {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/home")
    public String home(){
        return "home";
    }

    @RequestMapping("/goods")
    public String goods(){
        return "goods";
    }

    @RequestMapping("/my_settings")
    public String my_settings(){
        return "my_settings";
    }

    @RequestMapping("/release")
    public String release(){
        return "release";
    }

    @RequestMapping("/sign_in")
    public String sign_in(){
        return "sign_in";
    }

    @RequestMapping("/sign_up")
    public String sign_up(){
        return "sign_up";
    }

    @RequestMapping("/user")
    public String user(){
        return "user";
    }

    @RequestMapping("/user_guest")
    public String user_guest(){
        return "user_guest";
    }

}
