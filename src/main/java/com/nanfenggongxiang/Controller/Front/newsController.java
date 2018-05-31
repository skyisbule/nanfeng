package com.nanfenggongxiang.Controller.Front;

import com.nanfenggongxiang.Dao.NewsCommentMapper;
import com.nanfenggongxiang.Dao.NewsMapper;
import com.nanfenggongxiang.Domain.News;
import com.nanfenggongxiang.Domain.NewsComment;
import com.nanfenggongxiang.Domain.NewsCommentExample;
import com.nanfenggongxiang.Domain.NewsExample;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by skyisbule on 2018/3/27.
 * 资讯板块
 */
@Api(description = "资讯模块相关的api")
@RestController
public class newsController {

    @Autowired
    NewsMapper dao;

    @Autowired
    NewsCommentMapper replyDao;

    /**
     * 查询资讯的api
     * @param page 第几页，从0开始
     * @param type 所属分类，注意：如果填0则按时间排序返回所有分类。
     * @return new的实体。
     */
    @ApiOperation("按页码数获取资讯，每页10条，page从0开始传。")
    @RequestMapping("/public/news/get-by-page")
    public List<News> getByPage(int page,int type,int isTop){
        NewsExample e = new NewsExample();
        e.setOffset(page*10);
        e.setLimit(10);
        e.setOrderByClause("nid desc");
        //如果type传0，那么就搜索全部
        if (type==0){
            e.createCriteria()
                    .andIsTopEqualTo(isTop);
            return dao.selectByExample(e);
        }else{
            e.createCriteria()
                    .andIsTopEqualTo(isTop)
                    .andClassificationEqualTo(type);
            return dao.selectByExample(e);
        }
    }

    @ApiOperation("添加一条资讯，传实体就好。")
    @RequestMapping("/admin/news/add")
    public String add(News news){
        Date date = new Date();
        news.setReleaseTime(date);
        dao.insert(news);
        return "success";
    }

    @ApiOperation("根据资讯的id更新资讯信息，传什么更新什么。")
    @RequestMapping("/admin/news/update")
    public String update(News news){
        Date date = new Date();
        news.setReleaseTime(date);
        dao.updateByPrimaryKey(news);
        return "success";
    }

    @ApiOperation("根据资讯的id删除资讯，同时也会删除这条资讯下边的回复。")
    @RequestMapping("/admin/news/delete")
    public String delete(int newsId){
        dao.deleteByPrimaryKey(newsId);
        NewsCommentExample e = new NewsCommentExample();
        e.createCriteria()
                .andNidEqualTo(newsId);
        replyDao.deleteByExample(e);
        return "success";
    }

}
