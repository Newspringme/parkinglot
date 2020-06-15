package com.cnzk.service;


import com.cnzk.mapper.FaceMapper;
import com.cnzk.pojo.Admin;
import com.cnzk.pojo.Charge;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FaceServiceImpl implements FaceService {

    @Resource
    private FaceMapper faceMapper;



    @Override
    public Admin findAdminByAccount(String adminnum) {

        return faceMapper.findAdminByAccount(adminnum);
    }



    @Override
    public Charge findChargeByAccount(String adminnum) {
        return faceMapper.findChargeByAccount(adminnum);
    }
}
