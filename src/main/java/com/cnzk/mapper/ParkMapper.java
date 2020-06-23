package com.cnzk.mapper;

import com.cnzk.pojo.Charge;
import com.cnzk.pojo.TbBill;
import com.cnzk.pojo.TbPark;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ParkMapper {
    //查询车位情况
    List<TbPark> queryPark();

    //车辆离开车位
    int carExit(TbBill bill);

    //获取车位列表
    List<TbPark> getParkList(@Param("tbPark") TbPark tbPark, @Param("start") int start, @Param("pageSize") int pageSize);

    //获取车位数量
    Integer getParkCount(TbPark tbPark);

    //通过车牌获取车位
    TbPark queryParkByCarNum(@Param("carNum") String carNum);
}
