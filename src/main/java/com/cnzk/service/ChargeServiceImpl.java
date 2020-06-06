package com.cnzk.service;

import com.cnzk.mapper.ChargeMapper;
import com.cnzk.pojo.Charge;
import com.cnzk.pojo.LayuiData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ChargeServiceImpl implements ChargeService {

    @Resource
    private ChargeMapper chargeMapper;

    @Override
    public LayuiData queryCharge(Charge charge, int start, int pageSize) {
        List<Charge> list = chargeMapper.queryChargeList(charge,start,pageSize);
        int count = chargeMapper.queryChargeCount(charge);
        LayuiData layuiData = new LayuiData();
        layuiData.setCount(count);
        layuiData.setData(list);
        layuiData.setCode(0);
        return layuiData;
    }
}
