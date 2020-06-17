package com.cnzk.mapper;

import com.cnzk.pojo.TbCar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author su
 * @date 2020/6/12-9:14
 */
@Mapper
public interface CarMapper
{
	//	查询记录数
	int queryWhitListCount();

	//	获取白名单列表
	List<TbCar> queryWhiteList(@Param("start") int start, @Param("pageSize") int pageSize);

	//	车辆白名单录入
	int addWhiteList(String carNum);

	//	车辆白名单删除
	int deleteWhiteList(String carNum);

	//	根据车牌查询车表记录
	TbCar queryCarByCarNum(String carNum);

	//	添加车辆信息
	int addCar(TbCar tbCar);

	//	添加用户与车关系信息
	int addUserCar(TbCar tbCar);

	//	开通过新月缴
	int handlePackage(TbCar tbCar);

	//	办理续费
	int handleRenew(TbCar tbCar);


}
