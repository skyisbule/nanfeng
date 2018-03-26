package com.nanfenggongxiang.Dao;

import com.nanfenggongxiang.Domain.NewsType;
import com.nanfenggongxiang.Domain.NewsTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NewsTypeMapper {
    long countByExample(NewsTypeExample example);

    int deleteByExample(NewsTypeExample example);

    int deleteByPrimaryKey(Integer typeId);

    int insert(NewsType record);

    int insertSelective(NewsType record);

    List<NewsType> selectByExample(NewsTypeExample example);

    NewsType selectByPrimaryKey(Integer typeId);

    int updateByExampleSelective(@Param("record") NewsType record, @Param("example") NewsTypeExample example);

    int updateByExample(@Param("record") NewsType record, @Param("example") NewsTypeExample example);

    int updateByPrimaryKeySelective(NewsType record);

    int updateByPrimaryKey(NewsType record);
}