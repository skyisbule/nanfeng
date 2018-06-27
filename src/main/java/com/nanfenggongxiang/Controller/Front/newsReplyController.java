package com.nanfenggongxiang.Controller.Front;

import com.nanfenggongxiang.Dao.NewsCommentDao;
import com.nanfenggongxiang.Dao.NewsCommentMapper;
import com.nanfenggongxiang.Domain.NewsComment;
import com.nanfenggongxiang.Domain.PostReply;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by skyisbule on 2018/3/28.
 * 咨询的留言表
 */
@Api("资讯下边的回复的相关api。")
@RestController
@RequestMapping(value = "/",method = {RequestMethod.GET,RequestMethod.POST})
public class newsReplyController {

    @Autowired
    NewsCommentMapper dao;
    @Autowired
    NewsCommentDao    complexDao;

    @ApiOperation("添加一条回复，不用传用户id。")
    @RequestMapping("/private/newsReply/add")
    public String add(@CookieValue("uid")int uid
                    ,NewsComment comment){
        Date date = new Date();
        comment.setReleaseTime(date);
        comment.setUid(uid);
        return dao.insert(comment)==1?"success":"error";
    }

    @ApiOperation("根据页码获取资讯的回复，同上，从0开始。")
    @RequestMapping("/public/newsReply/get-by-page")
    public List<Map<String,Object>> getByPage(int newsId,int page) {
        return complexDao.getByPage(page * 10, newsId);
    }

    @ApiOperation("删掉自己的回复。")
    @RequestMapping("/private/newReply/delete")
    public String delete(int replyId){
        dao.deleteByPrimaryKey(replyId);
        return "success";
    }

}
