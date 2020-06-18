package com.cnzk.controller;

import com.alibaba.fastjson.JSON;
import com.cnzk.mapper.CarMapper;
import com.cnzk.pojo.LayuiData;
import com.cnzk.pojo.TbCar;
import com.cnzk.pojo.TbUser;
import com.cnzk.service.CarService;
import com.cnzk.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author su
 * @date 2020/6/12-9:28
 */
@Controller
@RequestMapping("CarController")
public class CarController
{
	@Resource
	private CarService carService;
	@Resource
	private CarMapper carMapper;
	@Resource
	private UserService userService;

	//	获取车辆白名单列表
	@RequestMapping("queryWhiteList")
	@ResponseBody
	public Object queryWhiteList(String page, String limit)
	{
//		获取起始页码
		int startPage = Integer.parseInt(page);
//		每页数量
		int pageSize = Integer.parseInt(limit);
//		计算查询位置
		int start = (startPage - 1) * pageSize;
		List<TbCar> list = carService.queryWhiteList(start, pageSize);
		LayuiData layuiData = new LayuiData();
		layuiData.setCode(0);
		layuiData.setCount(carMapper.queryWhitListCount());
		layuiData.setData(list);
		System.out.println("layuiData = " + JSON.toJSONString(layuiData));
		return layuiData;
	}

	//	车辆白名单的录入
	@RequestMapping("/addWhiteList")
	@ResponseBody
	public Object addWhiteList(String msg)
	{
		TbCar tbCar = new TbCar();
		if (msg != null && !"".equals(msg.trim()))
		{
			tbCar = JSON.parseObject(msg, TbCar.class);
		}
		System.out.println("白名单tbCar ========= " + tbCar);

		TbUser tbUser = userService.queryUserByUserName(tbCar.getUserName());
		TbUser tbUser1 = null;
		if (tbUser == null)
		{
			tbUser1 = new TbUser();
			tbUser1.setUserName(tbCar.getUserName());
			tbUser1.setUserTel(tbCar.getUserTel());
			int num = userService.addUser(tbUser1);
			if(num>0){
				System.out.println("用户添加成功") ;
			}
		}
		System.out.println("白名单用户tbUser1 ====== " + tbUser1);
		TbCar tbCar1 = carService.queryCarByCarNum(tbCar.getCarNum());
		System.out.println("tbCar1 ========== " + tbCar1);
		boolean bool = false;
		if (tbCar1 != null)
		{
			int num = carService.addWhiteList(tbCar.getCarNum());
			if (num > 0)
			{
				System.out.println("添加成功");
				bool = true;
			}
		}
		else
		{
			//车表数据不存在就要添加车数据和用户与车关系表
			int num = carService.addCar(tbCar);
			tbCar.setUserId((int) tbUser1.getUserId());
			int num1 = carService.addUserCar(tbCar);
			if (num1 > 0)
			{
				System.out.println("用户与车关系添加成功");
				bool = true;
			}
		}
		return bool;
	}

	//	车辆白名单的删除
	@RequestMapping("/deleteWhiteList")
	@ResponseBody
	public Object deleteWhiteList(String carNum)
	{
		boolean bool = false;
		int num = carService.deleteWhiteList(carNum);
		if (num > 0)
		{
			bool = true;
		}
		return bool;
	}

	//	通过车牌号验证车辆身份
	@ResponseBody
	@RequestMapping("verifyCar")
	public Object verifyCar(String carNum)
	{
		TbCar tbCar = carService.queryCarByCarNum(carNum);
		System.out.println("车辆信息tbCar ========== " + tbCar);
		return tbCar;
	}

	//  开通新月缴
	@ResponseBody
	@RequestMapping("handlePackage")
	public Object handlePackage(String carNum, String comboId)
	{
		TbCar tbCar = new TbCar();
		tbCar.setCarNum(carNum);
		tbCar.setComboId(Integer.parseInt(comboId));
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		switch (tbCar.getComboId())
		{
			case 1:
				calendar.add(Calendar.MONTH, 1);
				break;
			case 2:
				calendar.add(Calendar.MONTH, 3);
				break;
			case 3:
				calendar.add(Calendar.MONTH, 6);
				break;
			case 4:
				calendar.add(Calendar.MONTH, 12);
				break;
			case 5:
				calendar.add(Calendar.MONTH, 36);
				break;
			default:
				break;
		}
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		tbCar.setEndTime(sd.format(calendar.getTime()));
		int num = carService.handlePackage(tbCar);
		boolean bool = false;
		if (num > 0)
		{
			bool = true;
		}
		return bool;
	}

	//  办理续费
	@ResponseBody
	@RequestMapping("handleRenew")
	public Object handleRenew(String carNum,String addTime) throws ParseException
	{
		TbCar tbCar = carService.queryCarByCarNum(carNum);
		if(tbCar==null){
		    return "isNull";
		}else {
			String str = tbCar.getEndTime();
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sd.parse(str);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, Integer.parseInt(addTime));
			tbCar.setEndTime(sd.format(calendar.getTime()));
			int num = carService.handleRenew(tbCar);
			boolean bool = false;
			if (num > 0)
			{
				bool = true;
				System.out.println(" 续费办理成功");
			}
			return bool;
		}
	}


}
