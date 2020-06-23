package com.cnzk.service;

import com.cnzk.mapper.CoorMapper;
import com.cnzk.pojo.TbCoordinate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CoorServiceImpl implements CoorService {

    @Resource
    private CoorMapper coorMapper;

    @Override
    public TbCoordinate getSelfServiceCoor(String machineNum) {
        return coorMapper.getSelfServiceCoor(machineNum);
    }
}
