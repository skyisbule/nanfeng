package com.nanfenggongxiang.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by skyisbule on 2018/3/26.
 */
@Mapper
public interface MessageDao {

    @Select("select count(*) from message where gid=#{gid}")
    public int getCount(@Param("gid")int gid);

    @Select("select message.*,info.nick_name,info.head_pic,info.uid " +
            "from message join info on message.releaser = info.uid " +
            "where message.gid = ${gid} " +
            "limit ${page},10 ")
    public List<Map<String,Object>> getMessageByPageWithInfo(@Param("page")int page,
                                                             @Param("gid") int gid);

}