package com.cnzk.service;

import com.cnzk.pojo.LayuiData;
import com.cnzk.pojo.TbBill;
import com.cnzk.pojo.TbPark;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParkService {
    //查询车位情况
    List<TbPark> queryPark();

    int carExit(TbBill bill);

    //获取场内车位信息
    LayuiData getParkList(TbPark tbPark, int start,  int pageSize);
}
