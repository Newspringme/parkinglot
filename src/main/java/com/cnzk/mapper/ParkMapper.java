package com.cnzk.mapper;

import com.cnzk.pojo.TbPark;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ParkMapper {
    //查询车位情况
    List<TbPark> queryPark();


}
