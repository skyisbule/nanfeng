package com.nanfenggongxiang.Controller.Front;

import com.nanfenggongxiang.Dao.PostReplyDao;
import com.nanfenggongxiang.Dao.PostReplyMapper;
import com.nanfenggongxiang.Domain.PostReply;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by skyisbule on 2018/3/28.
 */
@RestController
public class postReplyController {

    @Autowired
    PostReplyMapper dao;
    @Autowired
    PostReplyDao    complexDao;

    @RequestMapping("/private/postReply/add")
    public String add(PostReply reply){
        //给帖子的回复数加一
        complexDao.addReplyNum(reply.getPostId());
        
        Date date = new Date();
        reply.setReleaseTime(date);
        reply.setIsDelete(0);
        return dao.insert(reply)==1?"success":"error";
    }

    @RequestMapping("/public/postReply/get-by-page")
    public List<Map<String,Object>> GetByPage(int page, int postId){
        return complexDao.getPostReplyByPage(page,postId);
    }

}
