package com.nanfenggongxiang.Dao;

import com.nanfenggongxiang.Domain.PostReply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * Created by skyisbule on 2018/3/28.
 */
@Mapper
public interface PostReplyDao {

    @Select("select post_reply.*,info.nick_name,info.head_pic " +
            "from post_reply join info on " +
            "info.uid = post_reply.uid " +
            "where post_reply.post_id = #{postId} " +
            "order by post_reply.rid ASC " +
            "limit #{page},10;")
    public List<Map<String,Object>> getPostReplyByPage(@Param("page")int page, @Param("postId")int postId);

    @Update("update post set reply_num = reply_num + 1 where post.pid =#{pid};")
    public void addReplyNum(@Param("pid")int pid);

}
