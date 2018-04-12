package com.nanfenggongxiang.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * Created by skyisbule on 2018/3/27.
 */
@Mapper
public interface CommodityDao {

    @Select("select commodity.*,info.nick_name,info.release_num,info.head_pic " +
            "from commodity join info on " +
            "commodity.uid = info.uid where " +
            "commodity.is_sell_out = ${sell} and commodity.is_want_by = ${buy} " +
            "order by gid desc " +
            "limit #{page},10")
    public List<Map<String,Object>> getCommodityAndUserInfoByPage(
                                                                  @Param("sell")int isSellOut,
                                                                  @Param("buy")int isWantBuy,
                                                                  @Param("page")int page
                                                                  );

    @Select("select commodity.*,info.nick_name,info.release_num,info.head_pic " +
            "from commodity join info on " +
            "commodity.uid = info.uid where " +
            "commodity.is_sell_out = ${sell} and commodity.is_want_by = ${buy} " +
            "and commodity.goods_type = ${type} "+
            "order by gid desc " +
            "limit ${page},10")
    public List<Map<String,Object>> getCommodityAndUserInfoByPageAndType(
            @Param("sell")int isSellOut,
            @Param("buy") int isWantBuy,
            @Param("page")int page,
            @Param("type")int type
    );


    @Select("select commodity.*,info.nick_name,info.release_num,info.head_pic,info.contact" +
            "       from commodity join info on " +
            "commodity.uid = info.uid where " +
            "commodity.gid = #{gid}" )
    public Map<String,Object> getCommodityAndUserByGid(@Param("gid") int gid);

    @Select("select count(*) from commodity where uid = #{uid} and is_sell_out=#{isSellOut}")
    public Integer getCommoditySellOutCount(@Param("uid")int uid,@Param("isSellOut")int isSellOut);

    @Select("select commodity.*,info.nick_name,info.release_num,info.head_pic " +
            "from commodity join info on " +
            "commodity.uid = info.uid where " +
            "commodity.is_sell_out = ${sell} and commodity.is_want_by = ${buy} " +
            "and commodity.goods_type = ${type} "+
            "and commodity.goods_name like ${name} "+
            "order by gid desc " +
            "limit ${page},10")
    public List<Map<String,Object>> getCommodityAndUserInfoByPageAndTypeAndGoodsName(
            @Param("sell")int isSellOut,
            @Param("buy") int isWantBuy,
            @Param("page")int page,
            @Param("type")int type,
            @Param("names")String name
    );

    @Update("update commodity is_sell_out = 1 where gid = #{gid}")
    public int tagSellOut(@Param("gid") int gid);

}
