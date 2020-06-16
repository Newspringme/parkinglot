package com.cnzk.mapper;

import com.cnzk.pojo.TbBill;
import com.cnzk.pojo.TbPark;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ParkMapper {
    //查询车位情况
    List<TbPark> queryPark();

    //车辆离开车位
    int carexit(TbBill bill);

}
