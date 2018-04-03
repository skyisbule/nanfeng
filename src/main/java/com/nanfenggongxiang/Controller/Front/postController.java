package com.nanfenggongxiang.Controller.Front;

import com.nanfenggongxiang.Dao.PostMapper;
import com.nanfenggongxiang.Domain.Post;
import com.nanfenggongxiang.Domain.PostExample;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

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

    @ApiOperation("添加一条帖子，时间、回复数不用填")
    @RequestMapping("/private/post/add")
    public String add(Post post){
        Date date = new Date();
        post.setReleaseTime(date);
        post.setReplyNum(1);
        return dao.insert(post)==1?"success":"error";
    }

    @ApiOperation("根据页码拿到帖子信息")
    @RequestMapping(value = "/public/post/get-by-page",method = RequestMethod.GET)
    public List<Post> getByPage(@ApiParam("开始页码") int page,
                                @ApiParam("是否置顶") int isTop,
                                @ApiParam("哪个版块") int plate){
        PostExample e = new PostExample();
        e.setOffset(page*10);
        e.setLimit(10);
        e.createCriteria()
                .andIsTopEqualTo(isTop)
                .andPlateIdEqualTo(plate);
        return dao.selectByExample(e);
    }

    @RequestMapping("/private/post/update")
    public String updateById(Post post){
        return dao.updateByPrimaryKey(post)==1?"success":"error";
    }

}
