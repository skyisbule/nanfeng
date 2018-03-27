package com.nanfenggongxiang.Controller.Front;

import com.nanfenggongxiang.Dao.CommodityMapper;
import com.nanfenggongxiang.Dao.InfoMapper;
import com.nanfenggongxiang.Domain.Commodity;
import com.nanfenggongxiang.Domain.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by skyisbule on 2018/3/27.
 */
@RestController
public class test {

    @Autowired
    InfoMapper dao;

    @Autowired
    CommodityMapper comDao;


    @RequestMapping("/test")
    public String add(Commodity commodity){
        commodity.setReleaseTime(new Date());
        comDao.insert(commodity);
        return "1;";
    }

}
