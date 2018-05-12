package com.nanfenggongxiang.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by skyisbule on 2018/5/12.
 */
@Mapper
public interface infoDao {

    @Select("select info.contact,info.tel_num,info.qq,info.wechat from info where uid = (select uid from commodity where gid = ${gid})")
    public List<Map<String,Object>> getOrderUserInfoByGid(@Param("gid")int gid);

}
