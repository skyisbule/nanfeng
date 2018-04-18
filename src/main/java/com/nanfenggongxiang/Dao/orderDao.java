package com.nanfenggongxiang.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by skyisbule on 2018/4/12.
 */
@Mapper
public interface orderDao {

    @Select("select commodity.* ,orders.* from orders join commodity on commodity.uid = orders.uid where orders.uid = #{uid}")
    public List<Map<String,Object>> getByUid(@Param("uid")int uid);

}
