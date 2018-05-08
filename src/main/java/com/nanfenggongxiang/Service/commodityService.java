package com.nanfenggongxiang.Service;

import com.nanfenggongxiang.Dao.CommodityDao;
import com.nanfenggongxiang.Dao.CommodityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by skyisbule on 2018/4/14.
 */
@Service
public class commodityService {

    @Autowired
    CommodityMapper dao;
    @Autowired
    CommodityDao    complexDao;

    public void addReleaseNum(int uid){
        complexDao.addReleaseNum(uid);
    }

    @Transactional(propagation = Propagation.REQUIRED,
            isolation   = Isolation.DEFAULT,
            timeout     = 3600,
            rollbackFor = Exception.class)
    public boolean delectCommodity(int gid){
        try {
            //用户表里用户的发布商品数减一
            complexDao.reduceUserReleaseNum(gid);
            //然后彻底删除掉该商品
            dao.deleteByPrimaryKey(gid);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
