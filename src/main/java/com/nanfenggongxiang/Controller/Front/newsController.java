package com.nanfenggongxiang.Controller.Front;

import com.nanfenggongxiang.Dao.NewsCommentMapper;
import com.nanfenggongxiang.Dao.NewsMapper;
import com.nanfenggongxiang.Domain.News;
import com.nanfenggongxiang.Domain.NewsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by skyisbule on 2018/3/27.
 * 资讯板块
 */
@RestController
public class newsController {

    @Autowired
    NewsMapper dao;

    /**
     * 查询资讯的api
     * @param page 第几页，从0开始
     * @param type 所属分类，注意：如果填0则按时间排序返回所有分类。
     * @return new的实体。
     */
    @RequestMapping("/public/news/get-by-page")
    public List<News> getByPage(int page,int type){
        NewsExample e = new NewsExample();
        e.setOffset(page*10);
        e.setLimit(10);
        e.setOrderByClause("nid desc");
        //如果type传0，那么就搜索全部
        if (type==0){
            return dao.selectByExample(e);
        }else{
            e.createCriteria()
                    .andClassificationEqualTo(type);
            return dao.selectByExample(e);
        }
    }

    @RequestMapping("/admin/news/add")
    public String add(News news){
        dao.insert(news);
        return "success";
    }

    @RequestMapping("/admin/news/update")
    public String update(News news){
        dao.updateByPrimaryKey(news);
        return "success";
    }

}
