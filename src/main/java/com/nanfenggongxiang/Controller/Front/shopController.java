package com.nanfenggongxiang.Controller.Front;

import com.nanfenggongxiang.Dao.CommodityDao;
import com.nanfenggongxiang.Dao.CommodityMapper;
import com.nanfenggongxiang.Domain.Commodity;
import com.nanfenggongxiang.Domain.CommodityExample;
import groovy.util.MapEntry;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by skyisbule on 2018/3/26.
 * 控制商品
 */
@Api(description = "商品相关的api")
@RestController
@RequestMapping(value = "",method = RequestMethod.POST)
public class shopController {

    @Autowired
    CommodityMapper dao;
    @Autowired
    CommodityDao    complexDao;

    /**
     * 增加一件商品，需要已经登录。
     * @param uid        从cookie中读取用户的id
     * @param commodity  商品实体类，注意我会做一些初始化。
     * @See   Domain.Commodity
     * @return 成功或失败
     */
    @ApiOperation("添加一条商品")
    @RequestMapping("/private/commodity/add")
    public String addCommodity(
            @CookieValue("uid")int uid,
            Commodity commodity){
        commodity.setUid(uid);
        //初始化
        init(commodity);
        Date date = new Date();
        commodity.setReleaseTime(date);
        return dao.insert(commodity)==1?"success":"false";
    }


    /**
     *
     * 这个api用于首页，它会返回商品信息和发布者的信息。
     *
     * @param page      页码数，从0开始，每次给你返回10条
     * @param isWantBy  卖还是买    想买是1   想卖是0
     * @param isSellOut 交易是否关闭，即是否处在交易状态，1是上架中，0表示已经下架。
     * @return          一个json数组，内容为商品的实体
     */
    @ApiOperation("按页码数返回商品信息和发布者信息")
    @RequestMapping(value = "/public/commodity/get-with-info-by-page",method = RequestMethod.GET)
    public List<Map<String,Object>> getCommodityAndUserInfoByPage(
                                                                  @ApiParam("是否被卖出去了，传1和0")int isSellOut,
                                                                  @ApiParam("是想买还是想卖")int isWantBy,
                                                                  @ApiParam("页码数，从0开始") int page,
                                                                  @ApiParam("分类")int goodsType
                                                                  ){
        if (goodsType==0)
            return complexDao.getCommodityAndUserInfoByPage(isSellOut,isWantBy,page*10);
        else
            return complexDao.getCommodityAndUserInfoByPageAndType(isSellOut,isWantBy,page*10,goodsType);
    }

    @ApiOperation("通过商品id获取商品详情")
    @RequestMapping(value = "/public/commodity/get-with-info-by-gid",method = RequestMethod.GET)
    public Map<String,Object> getCommodityAndUserInfoByGid(@ApiParam("商品id")int gid){
        Map<String,Object> map = complexDao.getCommodityAndUserByGid(gid);
        Integer uid = Integer.parseInt(map.get("uid").toString());
        Integer isSellOut = Integer.parseInt(map.get("is_sell_out").toString());
        //拿到在售的宝贝有多少件
        Object count = complexDao.getCommoditySellOutCount(uid,isSellOut);
        map.put("selling",count);
        return map;
    }

    @ApiOperation("获取某用户有多少宝贝在售")
    @RequestMapping(value = "/public/commodity/get-selling-count",method = RequestMethod.GET)
    public Integer getSellingCount(@ApiParam("用户id") int uid,
                                   @ApiParam("是否卖出") int isSellOut){
        return complexDao.getCommoditySellOutCount(uid,isSellOut);
    }

    private void init(Commodity commodity){
        commodity.setGid(null);
        //commodity.setUid(uid);
        commodity.setPageViews(1);
        commodity.setReleaseTime(new Date());
        commodity.setIsSellOut(0);
    }


}
