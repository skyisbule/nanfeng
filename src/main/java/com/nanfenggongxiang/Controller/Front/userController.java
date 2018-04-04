package com.nanfenggongxiang.Controller.Front;

import cn.hutool.core.util.HashUtil;
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
            @ApiResponse(code = 200,message = "返回 success 或者 noThisUser 、wrongPasswd"),
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

    @RequestMapping(value = "/regist")
    public String regist(Info info){
        //初始化注册信息
        registInit(info);
        if (dao.insert(info)==1){
            return "success";
        }else {
            return "error";
        }
    }

    @ApiOperation("修改用户信息的接口")
    @RequestMapping(value = "/private/user/update")
    public String update(Info info){
        return dao.updateByPrimaryKey(info)==1?"success":"error";
    }

    /**
     * 获取当前用户名是否可以注册
     * @param nickName  昵称  也就是注册登录用的名字
     * @return
     */
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

    /**
     * 获取手机号是否被使用
     * @param tel  手机号
     * @return
     */
    @ApiOperation("判断该手机号有没有被注册")
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

    @ApiOperation("通过用户id获取用户的信息")
    @RequestMapping("/public/user/get-info-by-id")
    public Info getInfoById(int uid){
        return dao.selectByPrimaryKey(uid);
    }


    private String getHash(String str){
        if (str != null && str.length() != 0){
            int hashCode = HashUtil.fnvHash(str);
            return String.valueOf(hashCode);
        }
        return "null";
    }

}
