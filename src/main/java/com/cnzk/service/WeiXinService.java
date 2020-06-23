package com.cnzk.service;

import com.cnzk.pojo.TbFeedback;

import com.cnzk.pojo.LayuiData;
import com.cnzk.pojo.TbBill;

import java.util.List;

public interface WeiXinService {
    List<String> queryImgUrl();
    Integer feedback(TbFeedback feedback);

    //    小程序订单查询
    LayuiData weiXinQueryBill(String carNum);

    //    根据订单编号查账单信息
    TbBill queryBilldetails(String carNum, String billNum);
}
