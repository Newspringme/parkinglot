package com.cnzk.mapper;

import com.cnzk.pojo.Admin;
import com.cnzk.pojo.Charge;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ChargeMapper {

    //	管理员登录
    Admin chargelogin(Map<String,Object> map);
    //查询收费员记录数
    Integer queryChargeCount(Charge charge);

    //查询收费员记录
    List<Charge> queryChargeList(@Param("admin") Charge charge, @Param("start") int start, @Param("pageSize") int pageSize);

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
