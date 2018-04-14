package com.nanfenggongxiang.Service;

import com.nanfenggongxiang.Dao.CommodityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by skyisbule on 2018/4/14.
 */
@Service
public class commodityService {

    @Autowired
    CommodityDao dao;

    public void addReleaseNum(int uid){
        dao.addReleaseNum(uid);
    }

}
