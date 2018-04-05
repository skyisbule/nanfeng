package com.nanfenggongxiang.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * Created by skyisbule on 2018/4/5.
 */
@Mapper
public interface countDao {

    @Update("update commodity set page_views = page_views+1 where gid=#{gid}")
    public void commodityPageViewsAdd(@Param("gid") int gid);

}
