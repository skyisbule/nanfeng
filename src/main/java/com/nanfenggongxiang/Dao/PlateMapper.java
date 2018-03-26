package com.nanfenggongxiang.Dao;

import com.nanfenggongxiang.Domain.Plate;
import com.nanfenggongxiang.Domain.PlateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlateMapper {
    long countByExample(PlateExample example);

    int deleteByExample(PlateExample example);

    int deleteByPrimaryKey(Integer plateId);

    int insert(Plate record);

    int insertSelective(Plate record);

    List<Plate> selectByExample(PlateExample example);

    Plate selectByPrimaryKey(Integer plateId);

    int updateByExampleSelective(@Param("record") Plate record, @Param("example") PlateExample example);

    int updateByExample(@Param("record") Plate record, @Param("example") PlateExample example);

    int updateByPrimaryKeySelective(Plate record);

    int updateByPrimaryKey(Plate record);
}