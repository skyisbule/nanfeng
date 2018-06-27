package com.nanfenggongxiang.Controller.Front;

import cn.hutool.core.util.HashUtil;
import com.google.gson.Gson;
import com.nanfenggongxiang.Dao.InfoMapper;
import com.nanfenggongxiang.Domain.Info;
import com.nanfenggongxiang.Domain.InfoExample;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(value = "user",description = "用户相关的接口")
@RestController
@RequestMapping(value = "",method = RequestMethod.POST)
public class userController {

    @Autowired
    InfoMapper dao;

    @ApiOperation("登录接口")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "返回 success 或者 账号错误 、密码错误"),
            @ApiResponse(code = 400,message = "前端输入了非法参数")
    })
    @RequestMapping(value = "/login")
    public String login(@ApiParam("用户手机号") String tel,
                        @ApiParam("密码")   String passwd,
                        HttpServletResponse response){
        InfoExample e = new InfoExample();
        e.createCriteria().
                andTelNumEqualTo(tel);
        List<Info> users = dao.selectByExample(e);
        //没有该用户
        if (users.size()==0)
            return "账号错误";
        //核对一下密码
        if (users.get(0).getPasswd().equals(passwd)){
            Info user  = users.get(0);
            int maxAge = 60*60*24 * 30 * 12;//30*12天过期
            Cookie idCookie     = new Cookie("uid",user.getUid().toString());
            Cookie passwdCookie = new Cookie("session",getHash(user.getPasswd()));
            Cookie headCookie   = new Cookie("headPic",user.getHeadPic());
            idCookie.setMaxAge(maxAge);
            passwdCookie.setMaxAge(maxAge);
            headCookie.setMaxAge(maxAge);
            idCookie.setPath("/");
            passwdCookie.setPath("/");
            headCookie.setPath("/");
            response.addCookie(idCookie);
            response.addCookie(passwdCookie);
            response.addCookie(headCookie);
            user.setPasswd(null);
            Gson gson = new Gson();
            return gson.toJson(user);

        }
        return "wrongPassword";
    }

    @RequestMapping(value = "/regist")
    public String regist(Info info){
        //初始化注册信息
        registInit(info);
        return dao.insert(info)==1?"success":"error";
    }

    @ApiOperation("修改用户信息的接口")
    @RequestMapping(value = "/private/user/update")
    public String update(Info info,HttpServletResponse response){
        int maxAge = 60*60*24 * 30 * 12;//30*12天过期
        Cookie headCookie   = new Cookie("headPic",info.getHeadPic());
        headCookie.setMaxAge(maxAge);
        headCookie.setPath("/");
        response.addCookie(headCookie);
        return dao.updateByPrimaryKey(info)==1?"success":"error";
    }

    @ApiOperation("判断该昵称是否被人注册过")
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


    @ApiOperation("判断该手机号有没有被注册")
    @RequestMapping(value = "/public/hasTelNum",method = RequestMethod.GET)
    public String hasTel(String tel){
        InfoExample e = new InfoExample();
        e.createCriteria()
                .andTelNumEqualTo(tel);
        List<Info> users = dao.selectByExample(e);
        if (users.size()==0)
            return "not exist";
        else
            return "hasExist";
    }


    private void registInit(Info info){
        info.setReleaseNum(0);
    }

    @ApiOperation("通过用户id获取用户的信息")
    @RequestMapping("/public/user/get-info-by-id")
    public Info getInfoById(int uid){
        return dao.selectByPrimaryKey(uid);
    }

    @ApiOperation("通过id获取用户的昵称")
    @RequestMapping("/public/user/get-nickName-by-id")
    public String getNickName(int uid){
        return dao.selectByPrimaryKey(uid).getNickName();
    }

    private String getHash(String str){
        if (str != null && str.length() != 0){
            int hashCode = HashUtil.fnvHash(str);
            return String.valueOf(hashCode);
        }
        return "null";
    }

}
