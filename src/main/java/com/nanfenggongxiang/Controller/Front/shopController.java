package com.nanfenggongxiang.Controller.Front;

import com.nanfenggongxiang.Dao.CommodityDao;
import com.nanfenggongxiang.Dao.CommodityMapper;
import com.nanfenggongxiang.Domain.Commodity;
import com.nanfenggongxiang.Domain.CommodityExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by skyisbule on 2018/3/26.
 * 控制商品
 */
@RestController
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
    @RequestMapping("/private/commodity/add")
    public String addCommodity(
            Integer uid
            //@CookieValue("id")Integer uid
            ,Commodity commodity){
        //初始化
        init(commodity,uid);
        return dao.insert(commodity)==1?"success":"false";
    }

    /**
     *
     * 注意：这个api不会返回个人信息
     *
     * @param page      页码数，从0开始，每次给你返回10条
     * @param isWantBy  卖还是买    想买是1   想卖是0
     * @param isSellOut 交易是否关闭，即是否处在交易状态，1是上架中，0表示已经下架。
     * @return          一个json数组，内容为商品的实体
     */
    @RequestMapping("/public/commodity/get-by-page")
    public List<Commodity> getByPage(int page,int isWantBy,int isSellOut){
        CommodityExample e = new CommodityExample();
        e.setOffset(page*10);
        e.setLimit(10);
        e.createCriteria()
                .andIsWantByEqualTo(isWantBy)
                .andIsSellOutEqualTo(isSellOut);
        return dao.selectByExample(e);
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
    @RequestMapping("/public/commodity/get-with-info-by-page")
    public List<Map<String,Object>> getCommodityAndUserInfoByPage(int page, int isWantBy, int isSellOut){
        return complexDao.getCommodityAndUserInfoByPage(page*10,isSellOut,isWantBy);
    }

    private void init(Commodity commodity,int uid){
        commodity.setGid(null);
        commodity.setUid(uid);
        commodity.setPageViews(1);
        commodity.setReleaseTime(new Date());
        commodity.setIsSellOut(0);
    }


}
