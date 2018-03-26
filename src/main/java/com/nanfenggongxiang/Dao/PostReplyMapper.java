package com.nanfenggongxiang.Dao;

import com.nanfenggongxiang.Domain.PostReply;
import com.nanfenggongxiang.Domain.PostReplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PostReplyMapper {
    long countByExample(PostReplyExample example);

    int deleteByExample(PostReplyExample example);

    int deleteByPrimaryKey(Integer rid);

    int insert(PostReply record);

    int insertSelective(PostReply record);

    List<PostReply> selectByExample(PostReplyExample example);

    PostReply selectByPrimaryKey(Integer rid);

    int updateByExampleSelective(@Param("record") PostReply record, @Param("example") PostReplyExample example);

    int updateByExample(@Param("record") PostReply record, @Param("example") PostReplyExample example);

    int updateByPrimaryKeySelective(PostReply record);

    int updateByPrimaryKey(PostReply record);
}