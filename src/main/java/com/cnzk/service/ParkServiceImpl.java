package com.cnzk.service;

import com.cnzk.mapper.ParkMapper;
import com.cnzk.pojo.LayuiData;
import com.cnzk.pojo.TbBill;
import com.cnzk.pojo.TbPark;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ParkServiceImpl implements ParkService {
    @Resource
    private ParkMapper parkMapper;

    @Override
    public List<TbPark> queryPark() {
        return parkMapper.queryPark();
    }

    @Override
    public int carExit(TbBill bill) {
        return parkMapper.carExit(bill);
    }

    @Override
    public LayuiData getParkList(TbPark tbPark, int start, int pageSize) {
        List<TbPark> list = parkMapper.getParkList(tbPark, start, pageSize);
        int count = parkMapper.getParkCount(tbPark);
        LayuiData layuiData = new LayuiData();
        layuiData.setCount(count);
        layuiData.setData(list);
        layuiData.setCode(0);
        return layuiData;
    }
}
