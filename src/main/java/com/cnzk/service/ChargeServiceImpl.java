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

    @Override
    public Integer updateState(Charge charge) {
        return chargeMapper.updateState(charge);
    }

    @Override
    public Integer insertNewCharge(Charge charge) {
        return chargeMapper.insertNewCharge(charge);
    }

    @Override
    public Integer searchCharge(String adminName) {
        return chargeMapper.searchCharge(adminName);
    }

    @Override
    public Integer logOff(String adminName) {
        return chargeMapper.logOff(adminName);
    }

    @Override
    public Integer resetPass(String adminName) {
        return chargeMapper.resetPass(adminName);
    }

    @Override
    public Integer uploadHeadImg(Charge charge) {
        return chargeMapper.uploadHeadImg(charge);
    }

    @Override
    public Integer updateCharge(Charge charge) {
        return chargeMapper.updateCharge(charge);
    }
}
