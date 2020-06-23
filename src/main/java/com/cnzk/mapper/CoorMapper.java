package com.cnzk.mapper;

import com.cnzk.pojo.TbCoordinate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CoorMapper {
    //获取自助终端坐标
    TbCoordinate getSelfServiceCoor(@Param("machineNum") String machineNum);

}
