package com.cnzk.service;

import com.cnzk.pojo.TbBill;

public interface BillService {
    //添加订单
    int insertBill (TbBill bill);
    //交易成功
    int updateBill (TbBill bill);

    TbBill getCarNum(TbBill bill);
}
