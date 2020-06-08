package com.cnzk.service;


import com.cnzk.pojo.Charge;
import com.cnzk.pojo.LayuiData;


public interface ChargeService {

    //	收费员，包括带条件,分页,记录数
    LayuiData queryCharge(Charge charge, int start, int pageSize);

    //更新状态
    Integer updateState(Charge charge);

    //添加新收费员
    Integer insertNewCharge(Charge charge);

    //查询收费员
    Integer searchCharge(String adminName);

    //收费员离职
    Integer logOff(String adminName);

    //重置密码
    Integer resetPass(String adminName);

    //上传头像
    Integer uploadHeadImg(Charge charge);

    //更新收费员
    Integer updateCharge(Charge charge);
}
