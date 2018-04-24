package com.nanfenggongxiang.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by skyisbule on 2018/4/5.
 */
@Mapper
public interface PostDao {

    @Select("select post.*,info.nick_name,info.head_pic,info.uid " +
            "from post join info on info.uid = post.uid " +
            "where plate_id= ${plateId} and is_top = ${isTop} " +
            "order by post.pid desc " +
            "limit ${page},10")
    public List<Map<String,Object>> getPostInfoByPage(@Param("plateId")int plateId,
                                                      @Param("page")   int page,
                                                      @Param("isTop")  int top);

    @Select("select post.*,info.nick_name,info.head_pic,info.uid " +
            "from post join info on info.uid = post.uid " +
            "order by post.pid desc " +
            "limit ${page},10")
    public List<Map<String,Object>> getAllPostInfoByPage(@Param("page")int page);

}
