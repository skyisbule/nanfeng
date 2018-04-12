package com.nanfenggongxiang.Controller.Front;

import com.nanfenggongxiang.Dao.AdvertisementMapper;
import com.nanfenggongxiang.Domain.Advertisement;
import com.nanfenggongxiang.Domain.AdvertisementExample;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;


/**
 * Created by skyisbule on 2018/4/12.
 */
@RestController
@RequestMapping(value = "/",method = {RequestMethod.GET,RequestMethod.POST})
public class advertiseController {

    @Autowired
    AdvertisementMapper dao;

    @ApiOperation("增加一条广告")
    @RequestMapping("/public/ad/add")
    public String add(Advertisement ad){
        ad.setTimes(new Date());
        dao.insert(ad);
        return "success";
    }

    @ApiOperation("更新一条广告，要传主键")
    @RequestMapping("/public/ad/update")
    public String update(Advertisement ad){
        dao.updateByPrimaryKey(ad);
        return "success";
    }

    @ApiOperation("获得所有广告")
    @RequestMapping("/public/ad/get-all")
    public List<Advertisement> getAll(){
        AdvertisementExample e = new AdvertisementExample();
        e.setOrderByClause("power dese");
        return dao.selectByExample(e);
    }

    @ApiOperation("删除一条广告")
    @RequestMapping("/public/ad/delete")
    public String delete(int adId){
        dao.deleteByPrimaryKey(adId);
        return "success";
    }

}
