package com.nanfenggongxiang.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
            "order by mid desc "+
            "limit ${page},10 ")
    public List<Map<String,Object>> getMessageByPageWithInfo(@Param("page")int page,
                                                             @Param("gid") int gid);

    @Select("select count(*) from message where receiver = #{uid} and is_readed = 0")
    public int getCountUnReaded(@Param("uid")int uid);

    @Update("update message set is_readed = 1 where gid = ${gid} and receiver = ${receiver}")
    public int tagReaded(@Param("receiver")int uid,@Param("gid")int gid);

    @Select("select message.*,info.nick_name,info.head_pic,info.uid " +
            "from message join info on message.releaser = info.uid " +
            "where message.receiver = ${uid} "+
            "and message.is_readed = ${isReaded} "+
            "order by mid desc " +
            "limit 0,10")
    public List<Map<String,Object>> getMessagesIfReaded(@Param("uid")int uid,
                                                        @Param("isReaded")int isReaded);
}