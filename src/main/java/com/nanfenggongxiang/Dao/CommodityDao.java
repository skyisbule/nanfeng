package com.nanfenggongxiang.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by skyisbule on 2018/3/27.
 */
@Mapper
public interface CommodityDao {

    @Select("select commodity.*,info.nick_name,info.release_num" +
            "       from commodity join info on " +
            "commodity.uid = info.uid where " +
            "commodity.is_sell_out = #{isSellOut} and commodity.is_want_by = #{isWantBuy} " +
            "order by gid desc " +
            "limit #{page},10;")
    public List<Map<String,Object>> getCommodityAndUserInfoByPage(@Param("page")int page,
                                                                  @Param("isSellOut")int isSellOut,
                                                                  @Param("isWantBuy")int isWantBuy);

    @Select("select commodity.*,info.nick_name,info.release_num,info.head_pic" +
            "       from commodity join info on " +
            "commodity.uid = info.uid where " +
            "commodity.gid = #{gid}" )
    public List<Map<String,Object>> getCommodityAndUserByGid(@Param("gid") int gid);

    @Select("select count(*) from commodity where uid = #{uid} and is_sell_out=#{isSellOut}")
    public Integer getCommoditySellOutCount(@Param("uid")int uid,@Param("isSellOut")int isSellOut);

}
