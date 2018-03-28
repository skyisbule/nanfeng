package com.nanfenggongxiang.Controller.Front;

import com.nanfenggongxiang.Dao.PostMapper;
import com.nanfenggongxiang.Domain.Post;
import com.nanfenggongxiang.Domain.PostExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by skyisbule on 2018/3/28.
 * 发布帖子
 */
@RestController
public class postController {

    @Autowired
    PostMapper dao;

    @RequestMapping("/private/post/add")
    public String add(Post post){
        Date date = new Date();
        post.setReleaseTime(date);
        post.setReplyNum(1);
        post.setIsTop(0);
        return dao.insert(post)==1?"success":"error";
    }

    @RequestMapping("/public/post/get-by-page")
    public List<Post> getByPage(int page,int isTop,int plate){
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
