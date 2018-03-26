package com.nanfenggongxiang.Util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by skyisbule on 2018/3/26.
 * 做鉴权
 */
public class auth {


    private static auth authUtil = new auth();
    private auth(){

    }

    public static auth getInstance(){
        return auth.authUtil;
    }

    class userInfo{
        private int     uid     = 0;
        private boolean checked = false;
    }

    public userInfo auth(HttpServletRequest request){
        Cookie[] cookies =  request.getCookies();
        String id     = getCookieValue("id",cookies);
        String passwd = getCookieValue("session",cookies);
        //todo 鉴权
        return new userInfo();
    }

    private String getCookieValue(String key,Cookie[] cookies){
        for (Cookie cookie : cookies){
            if (cookie.getName().equals(key)){
                return cookie.getValue();
            }
        }
        return null;
    }

}
