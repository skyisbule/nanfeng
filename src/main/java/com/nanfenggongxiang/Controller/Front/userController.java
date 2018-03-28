package com.nanfenggongxiang.Controller.Front;

import cn.hutool.core.util.HashUtil;
import com.nanfenggongxiang.Dao.InfoMapper;
import com.nanfenggongxiang.Domain.Info;
import com.nanfenggongxiang.Domain.InfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by skyisbule on 2018/3/26.
 * 控制用户相关的crud
 */
@RestController
public class userController {

    @Autowired
    InfoMapper dao;

    /**
     *
     * @param user       账号
     * @param passwd     密码
     * @return      无该用户、登陆成功、密码错误。
     */
    @RequestMapping(value = "/login")
    public String login(String user,
                        String passwd,
                        HttpServletResponse response){
        InfoExample e = new InfoExample();
        e.createCriteria().
                andNickNameEqualTo(user);
        List<Info> users = dao.selectByExample(e);
        //没有该用户
        if (users.size()==0)
            return "noThisUser";
        //核对一下密码
        if (users.get(0).getPasswd().equals(passwd)){
            Cookie idCookie = new Cookie("id",users.get(0).getUid().toString());
            Cookie passwdCookie = new Cookie("session",getHash(users.get(0).getPasswd()));
            response.addCookie(idCookie);
            response.addCookie(passwdCookie);
            return "success";

        }
        return "wrongPassword";
    }

    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    public String regist(Info info){
        //初始化注册信息
        registInit(info);
        if (dao.insert(info)==1){
            return "success";
        }else {
            return "error";
        }
    }

    /**
     * 获取当前用户名是否可以注册
     * @param nickName  昵称  也就是注册登录用的名字
     * @return
     */
    @RequestMapping("/public/hasNickName")
    public String isRegisted(String nickName){
        InfoExample e = new InfoExample();
        e.createCriteria()
                .andNickNameEqualTo(nickName);
        List<Info> users = dao.selectByExample(e);
        if (users.isEmpty())
            return "not exist";
        else
            return "hasExist";
    }

    /**
     * 获取手机号是否被使用
     * @param tel  手机号
     * @return
     */
    @RequestMapping("/public/hasTelNum")
    public String hasTel(String tel){
        InfoExample e = new InfoExample();
        e.createCriteria()
                .andNickNameEqualTo(tel);
        List<Info> users = dao.selectByExample(e);
        if (users.isEmpty())
            return "not exist";
        else
            return "hasExist";
    }


    private void registInit(Info info){
        info.setReleaseNum(0);
    }


    private String getHash(String str){
        if (str != null && str.length() != 0){
            int hashCode = HashUtil.fnvHash(str);
            return String.valueOf(hashCode);
        }
        return "null";
    }

}
