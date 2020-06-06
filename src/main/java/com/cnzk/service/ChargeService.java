package com.cnzk.service;


import com.cnzk.pojo.Charge;
import com.cnzk.pojo.LayuiData;


public interface ChargeService {

    //	收费员，包括带条件,分页,记录数
    LayuiData queryCharge(Charge charge, int start, int pageSize);
}
