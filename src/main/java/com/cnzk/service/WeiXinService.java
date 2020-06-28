package com.cnzk.service;

import com.cnzk.pojo.TbCar;
import com.cnzk.pojo.TbFeedback;

import com.cnzk.pojo.LayuiData;
import com.cnzk.pojo.TbBill;
import com.cnzk.pojo.TbUser;

import java.util.List;

public interface WeiXinService {
    List<String> queryImgUrl();
    Integer feedback(TbFeedback feedback);

    //    小程序订单查询
    LayuiData weiXinQueryBill(List<TbCar> tbCarList);

    Integer UpdateUser(TbUser user);
    //    根据订单编号查账单信息
    TbBill queryBilldetails(String billNum);

    //    查看空车位
    Integer queryNullPark();

//    根据手机号查车牌
    List<TbCar>  queryCarNum(String userTel);

    TbUser queryUser(String userTel);

}
