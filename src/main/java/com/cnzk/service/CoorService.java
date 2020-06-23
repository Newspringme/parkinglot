package com.cnzk.service;

import com.cnzk.pojo.TbCoordinate;
import org.apache.ibatis.annotations.Param;

public interface CoorService {
    //获取自助终端坐标
    TbCoordinate getSelfServiceCoor(@Param("machineNum") String machineNum);
}
