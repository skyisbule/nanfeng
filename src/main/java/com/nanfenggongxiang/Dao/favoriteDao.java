package com.nanfenggongxiang.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by skyisbule on 2018/4/11.
 */
@Mapper
public interface favoriteDao {

    @Select("select * from commodity where gid in (" +
            "select gid from favorites where uid = ${uid}) ")
    public List<Map<String,Object>> getFavoritesByUid(@Param("uid") int uid);


}
