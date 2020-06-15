package com.cnzk.service;

import com.cnzk.mapper.BillMapper;
import com.cnzk.mapper.RatesMapper;
import com.cnzk.pojo.TbBill;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class BillServiceImpl  implements  BillService{

    @Resource
    private BillMapper billMapper;
    @Override
    public int insertBill(TbBill bill) {
        return billMapper.insertBill(bill);
    }

    @Override
    public int updateBill(TbBill bill) {
        return billMapper.updateBill(bill);
    }
}
