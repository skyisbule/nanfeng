package com.nanfenggongxiang.Controller.Front;

import com.nanfenggongxiang.Dao.countDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by skyisbule on 2018/4/4.
 */
@Controller
@RequestMapping(value = "/",method = RequestMethod.GET)
public class view {

    @Autowired
    countDao commodity;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/home")
    public String home(){
        return "home";
    }

    @RequestMapping("/goods")
    public String goods(String gid){
        if(gid!=null){
            commodity.commodityPageViewsAdd(Integer.parseInt(gid));
        }
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

    @RequestMapping("/list")
    public String list(){
        return "list";
    }

    @RequestMapping("/my_message")
    public String my_message(){
        return "my_message";
    }

    @RequestMapping("/goods_list")
    public String goods_list(){
        return "goods_list";
    }

    @RequestMapping("/search")
    public String search(){
        return "search";
    }


    @RequestMapping("/information")
    public String infomation(){
        return "zx";
    }

    @RequestMapping("/community")
    public String bbs(){
        return "tb";
    }

    @RequestMapping("/list_0")
    public String list_0(){
        return "list_0";
    }

    @RequestMapping("/list_1")
    public String list_1(){
        return "list_1";
    }

    @RequestMapping("/list_2")
    public String list_2(){
        return "list_2";
    }

    @RequestMapping("/list_3")
    public String list_3(){
        return "list_3";
    }

    @RequestMapping("/admins")
    public String admin(){
        return "background";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
