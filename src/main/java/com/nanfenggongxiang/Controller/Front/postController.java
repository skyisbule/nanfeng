package com.nanfenggongxiang.Controller.Front;

import com.nanfenggongxiang.Dao.PostDao;
import com.nanfenggongxiang.Dao.PostMapper;
import com.nanfenggongxiang.Dao.PostReplyMapper;
import com.nanfenggongxiang.Domain.Post;
import com.nanfenggongxiang.Domain.PostExample;
import com.nanfenggongxiang.Domain.PostReply;
import com.nanfenggongxiang.Domain.PostReplyExample;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
 * 发布帖子
 */
@Api(description = "帖子相关的api")
@RestController
@RequestMapping(value = "",method = RequestMethod.POST)
public class postController {

    @Autowired
    PostMapper dao;
    @Autowired
    PostDao   complexDao;
    @Autowired
    PostReplyMapper replyDao;

    @ApiOperation("添加一条帖子，时间、回复数不用填")
    @RequestMapping("/private/post/add")
    public String add(Post post,
                      @CookieValue("uid")int uid
    ){
        post.setUid(uid);
        Date date = new Date();
        post.setReleaseTime(date);
        post.setReplyNum(1);
        return dao.insert(post)==1?"success":"error";
    }

    @ApiOperation("根据页码拿到帖子信息")
    @RequestMapping(value = "/public/post/get-by-page",method = RequestMethod.GET)
    public List<Map<String,Object>> getByPage(@ApiParam("开始页码") int page,
                               @ApiParam("是否置顶") int isTop,
                               @ApiParam("哪个版块") int plate){
        return complexDao.getPostInfoByPage(plate,page,isTop);
    }

    @RequestMapping("/private/post/update")
    public String updateById(Post post){
        return dao.updateByPrimaryKey(post)==1?"success":"error";
    }

    @RequestMapping("/private/post/set-top")
    public String updateById(int pid,int isTop){
        Post post = dao.selectByPrimaryKey(pid);
        if (post == null)
            return "null";
        post.setIsTop(isTop);
        return dao.updateByPrimaryKey(post)==1?"success":"error";
    }

    @RequestMapping("private/post/delete")
    public String delete(int pid){
        dao.deleteByPrimaryKey(pid);
        PostReplyExample e = new PostReplyExample();
        e.createCriteria()
                .andPostIdEqualTo(pid);
        return "success";
    }
}
