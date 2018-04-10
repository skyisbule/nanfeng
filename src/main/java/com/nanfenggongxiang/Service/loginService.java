package com.nanfenggongxiang.Service;

import cn.hutool.core.util.HashUtil;
import com.nanfenggongxiang.Dao.InfoMapper;
import com.nanfenggongxiang.Domain.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by skyisbule on 2018/4/10.
 * 处理用户鉴权相关的任务
 */
@Service
public class loginService {

    @Autowired
    InfoMapper dao;

    //全局的哈希函数
    public String getHash(String str){
        if (str != null && str.length() != 0){
            int hashCode = HashUtil.fnvHash(str);
            return String.valueOf(hashCode);
        }
        return "null";
    }

    //鉴权 看看uid和session对应的值一样不一样
    public boolean auth(int uid,String session){
        Info user = dao.selectByPrimaryKey(uid);
        String passwd  = user.getPasswd();
        String hashStr = this.getHash(passwd);
        return session.equals(hashStr);
    }

}
