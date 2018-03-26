package com.nanfenggongxiang.Dao;

import com.nanfenggongxiang.Domain.Favorites;
import com.nanfenggongxiang.Domain.FavoritesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FavoritesMapper {
    long countByExample(FavoritesExample example);

    int deleteByExample(FavoritesExample example);

    int insert(Favorites record);

    int insertSelective(Favorites record);

    List<Favorites> selectByExample(FavoritesExample example);

    int updateByExampleSelective(@Param("record") Favorites record, @Param("example") FavoritesExample example);

    int updateByExample(@Param("record") Favorites record, @Param("example") FavoritesExample example);
}