package com.nanfenggongxiang.Service;

import com.nanfenggongxiang.Dao.FavoritesMapper;
import com.nanfenggongxiang.Domain.Favorites;
import com.nanfenggongxiang.Domain.FavoritesExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by skyisbule on 2018/4/14.
 */
@Service
public class favoriteService {

    @Autowired
    FavoritesMapper dao;

    public boolean isAdded(int uid,int gid){
        FavoritesExample e = new FavoritesExample();
        e.createCriteria()
                .andUidEqualTo(uid)
                .andGidEqualTo(gid);
        List<Favorites> list = dao.selectByExample(e);
        return !list.isEmpty();
    }

}
