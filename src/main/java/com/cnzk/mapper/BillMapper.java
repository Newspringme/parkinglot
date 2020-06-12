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
}
