package com.cnzk.mapper;

import com.cnzk.pojo.Charge;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChargeMapper {


    //查询收费员记录数
    Integer queryChargeCount(Charge charge);

    //查询收费员记录
    List<Charge> queryChargeList(@Param("admin") Charge charge, @Param("start") int start, @Param("pageSize") int pageSize);
}
