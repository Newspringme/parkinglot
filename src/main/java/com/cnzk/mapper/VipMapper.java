package com.cnzk.mapper;


import com.cnzk.pojo.TbVip;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * create by: JiSyang
 * create time:  2020/6/22
*/
@Mapper
public interface VipMapper {

//    <!--根据id查询vip类型名-->
    TbVip queryVipInfo(@Param("vipId") int vipId);
}
