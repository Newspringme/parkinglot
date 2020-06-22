package com.cnzk.service;

import com.cnzk.mapper.CarMapper;
import com.cnzk.mapper.UserMapper;
import com.cnzk.pojo.TbCar;
import com.cnzk.pojo.TbExit;
import com.cnzk.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author su
 * @date 2020/6/12-9:29
 */
@Service
public class CarServiceImpl implements CarService
{
	@Resource
	private CarMapper carMapper;

	//获取白名单列表
	@Override
	public List<TbCar> queryWhiteList(int start, int pageSize)
	{
		return carMapper.queryWhiteList(start, pageSize);
	}

	//	车辆白名单录入
	@Override
	public int addWhiteList(String carNum)
	{
		return carMapper.addWhiteList(carNum);
	}

	//	车辆白名单删除
	@Override
	public int deleteWhiteList(String carNum)
	{
		return carMapper.deleteWhiteList(carNum);
	}

	//	根据车牌查询车表记录
	@Override
	public TbCar queryCarByCarNum(String carNum)
	{
		return carMapper.queryCarByCarNum(carNum);
	}

	//	添加车辆信息
	@Override
	public int addCar(TbCar tbCar)
	{
		return carMapper.addCar(tbCar);
	}

	//	添加用户与车关系信息
	@Override
	public int addUserCar(TbCar tbCar)
	{
		return carMapper.addUserCar(tbCar);
	}

	//	开通新月缴
	@Override
	public int handlePackage(TbCar tbCar)
	{
		return carMapper.handlePackage(tbCar);
	}
	//	办理续费
	@Override
	public int handleRenew(TbCar tbCar)
	{
		return carMapper.handleRenew(tbCar);
	}

	//  查询历史出场记录
	@Override
	public List<TbExit> queryExit(int start, int end) {return carMapper.queryExit(start,end);}

	//
	@Override
	public TbCar queryCarInfo(String carNum) {
		return carMapper.queryCarInfo(carNum);
	}
}
