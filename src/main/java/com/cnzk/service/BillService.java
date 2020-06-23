package com.cnzk.service;

import com.cnzk.pojo.TbBill;

public interface BillService {
    //添加订单
    int insertBill (TbBill bill);
    //交易成功
    int updateBill (TbBill bill);

    TbBill getCarNum(TbBill bill);

    //	查订单是否完成
    TbBill isSucceed(TbBill bill);
}
