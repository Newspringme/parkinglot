package com.cnzk.service;

import com.cnzk.pojo.TbBill;
import com.cnzk.pojo.TbPark;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParkService {
    //查询车位情况
    List<TbPark> queryPark();

    int carExit(TbBill bill);

}
