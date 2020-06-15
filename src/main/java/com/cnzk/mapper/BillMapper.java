package com.cnzk.mapper;

import com.cnzk.pojo.TbBill;
import org.apache.ibatis.annotations.Mapper;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;

@Mapper
public interface BillMapper {
    //	查收支明细
    List<TbBill> queryBill(@PathParam("map") HashMap<String, Object> map);
    //收支明细数量
    Integer queryBillCount(@PathParam("map") HashMap<String, Object> map);

    //查一周的总收入
    Integer findWeekAllCount();
    //统计一周中每天的临时停车收入
    Integer showWeekTempStatistics(HashMap<String, Object> condition);
    //统计一周中每天的月缴办理收入
    Integer showWeekComboStatistics(HashMap<String, Object> condition);


    //查一月的总收入
    Integer findMonthAllCount();
    //统计一月中每周的临时停车收入
    Integer showMonthTempStatistics(HashMap<String, Object> condition);
    //统计一月中每周的月缴办理收入
    Integer showMonthComboStatistics(HashMap<String, Object> condition);

    //查半年的总收入
    Integer findYearAllCount(HashMap<String, Object> condition);
    //统计半年中每月的临时停车收入
    Integer showYearTempStatistics(HashMap<String, Object> condition);
    //统计半年中每月的月缴办理收入
    Integer showYearComboStatistics(HashMap<String, Object> condition);
//    补充缺失当天的临时车收入
    Integer lackTempMoney(HashMap<String, Object> condition);
    //    补充缺失当天的临时车收入
    Integer lackComboMoney(HashMap<String, Object> condition);
    //添加订单
    int insertBill (TbBill bill);
    //交易成功
    int updateBill (TbBill bill);

    TbBill getCarNum(TbBill bill);
}
