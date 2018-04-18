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
            "and commodity.goods_name like #{names} "+
            "order by gid desc " +
            "limit ${page},10")
    public List<Map<String,Object>> getCommodityAndUserInfoByPageAndTypeAndGoodsName(
            @Param("sell")int isSellOut,
            @Param("buy") int isWantBuy,
            @Param("page")int page,
            @Param("type")int type,
            @Param("names")String name
    );

    @Update("update commodity set is_sell_out = 1 where gid = #{gid}")
    public int tagSellOut(@Param("gid") int gid);

    @Select("select commodity.*,info.nick_name,info.head_pic,info.contact " +
            "from commodity join info on  " +
            "info.uid = commodity.uid " +
            "where commodity.uid = ${uid} " +
            "and is_sell_out = ${isSellOut} " +
            "and is_want_by = ${isWantBuy} " +
            "order by commodity.gid desc " +
            "limit ${page},10")
    public List<Map<String,Object>> getMyRelease(@Param("uid")int uid,
                                                 @Param("isSellOut")int isSellOut,
                                                 @Param("isWantBuy")int isWantBuy,
                                                 @Param("page")int page);

    @Update("update info set release_num = release_num + 1 where uid = ${uid}")
    public void addReleaseNum(@Param("uid")int uid);

}
