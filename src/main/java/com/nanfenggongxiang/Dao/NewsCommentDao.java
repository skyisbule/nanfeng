package com.nanfenggongxiang.Dao;

import com.nanfenggongxiang.Domain.NewsComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by skyisbule on 2018/3/28.
 */
@Mapper
public interface NewsCommentDao {

    @Select("select news_comment.*,info.nick_name,info.head_pic " +
            "from news_comment join info on " +
            "news_comment.uid = info.uid " +
            "where news_comment.nid = ${newsId} " +
            "order by release_time ASC " +
            "limit #{page},10;")
    public List<Map<String,Object>> getByPage(@Param("page") int page, @Param("newsId")int newsId);

}
