package com.nanfenggongxiang.Controller.Front;

import com.nanfenggongxiang.Dao.PostReplyDao;
import com.nanfenggongxiang.Dao.PostReplyMapper;
import com.nanfenggongxiang.Domain.PostReply;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Api("社区模块内，帖子回复的相关api。")
@RestController
@RequestMapping(value = "/",method = {RequestMethod.GET,RequestMethod.POST})
public class postReplyController {

    @Autowired
    PostReplyMapper dao;
    @Autowired
    PostReplyDao    complexDao;

    private final String hasDelete = "该内容已被删除";

    @ApiOperation("添加一条帖子的留言，传实体。")
    @RequestMapping("/private/postReply/add")
    public String add(PostReply reply,
                      @CookieValue("uid")int uid){
        //给帖子的回复数加一
        complexDao.addReplyNum(reply.getPostId());
        
        Date date = new Date();
        reply.setReleaseTime(date);
        reply.setIsDelete(0);
        reply.setUid(uid);
        return dao.insert(reply)==1?"success":"error";
    }

    @ApiOperation("")
    @RequestMapping("/public/postReply/get-by-page")
    public List<Map<String,Object>> GetByPage(int page, int postId){
        List<Map<String, Object>> res = complexDao.getPostReplyByPage(page*10,postId);
        res.forEach((Map<String, Object> map) ->{
            if (map.get("is_delete").toString().equals("1"))
                map.put("content",this.hasDelete);
        });
        return res;
    }

    @RequestMapping("/private/postReply/delete")
    public String setDelete(int isDelete,int rid){
        complexDao.setDelete(isDelete,rid);
        return "success";
    }

}
