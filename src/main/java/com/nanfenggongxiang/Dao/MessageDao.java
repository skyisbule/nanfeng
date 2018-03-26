package com.nanfenggongxiang.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by skyisbule on 2018/3/26.
 */
@Mapper
public interface MessageDao {

    @Select("select count(*) from message where gid=#{gid}")
    public int getCount(@Param("gid")int gid);

}
