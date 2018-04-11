package com.nanfenggongxiang.Controller.Front;

import com.nanfenggongxiang.Dao.FavoritesMapper;
import com.nanfenggongxiang.Dao.favoriteDao;
import com.nanfenggongxiang.Domain.Favorites;
import com.nanfenggongxiang.Domain.FavoritesExample;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by skyisbule on 2018/4/10.
 * 收藏的api
 */

@Api("收藏功能的接口")
@RestController
public class favoriteController {

    @Autowired
    FavoritesMapper dao;
    @Autowired
    favoriteDao complexDao;

    @ApiOperation("获取某用户的收藏信息，返回所有")
    @RequestMapping("/private/favorite/get")
    public List<Map<String,Object>> getFavoriteByUid(int uid){
        return complexDao.getFavoritesByUid(uid);
    }

    @ApiOperation("传uid和gid，添加一条收藏")
    @RequestMapping("/private/favorite/add")
    public String add(Favorites favorites){
        if (favorites.getUid()!=null&&favorites.getGid()!=null){
            dao.insert(favorites);
            return "success";
        }
        return "error";
    }

    @ApiOperation("是否被收藏，返回“yes”，“no”")
    @RequestMapping("/private/favorite/is-favorited")
    public String isFavorited(int uid,int gid){
        FavoritesExample e = new FavoritesExample();
        e.createCriteria()
                .andGidEqualTo(gid)
                .andUidEqualTo(uid);
        List<Favorites> list = dao.selectByExample(e);
        return list==null?"no":"yes";
    }

}
