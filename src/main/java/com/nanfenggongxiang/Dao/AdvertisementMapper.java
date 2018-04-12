package com.nanfenggongxiang.Dao;

import com.nanfenggongxiang.Domain.Advertisement;
import com.nanfenggongxiang.Domain.AdvertisementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdvertisementMapper {
    long countByExample(AdvertisementExample example);

    int deleteByExample(AdvertisementExample example);

    int deleteByPrimaryKey(Integer aid);

    int insert(Advertisement record);

    int insertSelective(Advertisement record);

    List<Advertisement> selectByExample(AdvertisementExample example);

    Advertisement selectByPrimaryKey(Integer aid);

    int updateByExampleSelective(@Param("record") Advertisement record, @Param("example") AdvertisementExample example);

    int updateByExample(@Param("record") Advertisement record, @Param("example") AdvertisementExample example);

    int updateByPrimaryKeySelective(Advertisement record);

    int updateByPrimaryKey(Advertisement record);
}