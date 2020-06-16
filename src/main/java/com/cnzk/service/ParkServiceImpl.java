package com.cnzk.service;

import com.cnzk.mapper.ParkMapper;
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
        return null;
    }

    @Override
    public int carExit(TbBill bill) {
        return parkMapper.carexit(bill);
    }
}
