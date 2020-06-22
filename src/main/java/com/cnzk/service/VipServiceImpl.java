package com.cnzk.service;

import com.cnzk.mapper.VipMapper;
import com.cnzk.pojo.TbVip;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * create by: JiSyang
 * create time:  2020/6/22
 */
@Service
public class VipServiceImpl implements VipService {

    @Resource
    private VipMapper vipMapper;

    @Override
    public TbVip queryVipInfo(int vipId) {
        return vipMapper.queryVipInfo(vipId);
    }
}
