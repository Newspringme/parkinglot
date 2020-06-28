package com.cnzk.controller;

import com.alibaba.fastjson.JSON;
import com.cnzk.mapper.CarMapper;
import com.cnzk.pojo.*;
import com.cnzk.service.CarService;
import com.cnzk.service.UserService;
import com.cnzk.service.VipService;
import com.cnzk.websocket.WebSocket;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.websocket.Session;
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
	@Resource
	private VipService vipService;

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
	public Object addWhiteList(String carNum)
	{

		System.out.println("白名单carNumtbCar ========= " + carNum);

		TbCar tbCar = carService.queryCarByCarNum(carNum);
		if (tbCar != null)
		{
			int num = carService.addWhiteList(carNum);
			if (num > 0)
			{
				System.out.println("白名单添加成功");
				return "true";
			}else {
				return "false";
			}
		}
		else
		{
//			车辆不存在则要提示先绑定车辆
			return "noCar";
		}
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
	//  用户绑定车辆（要先查询车辆是否已经被绑定）
	@RequestMapping("addCar")
	@ResponseBody
	public Object addCar(String msg){
		System.out.println(" 绑定车辆msg ==== " + msg);
		if(msg!=null&&!"".equals(msg.trim())){
			TbCar tbCar=JSON.parseObject(msg,TbCar.class);
			TbCar tbCar1=carService.queryCarByCarNum(tbCar.getCarNum());
			if(tbCar1!=null){
//			    提示车辆已经被绑定
				return "haveCar";
			}else {
				int num=carService.addCar(tbCar);
				if (num>0){
					TbUser tbUser=userService.queryUserByUserName(tbCar.getUserName());
					tbCar.setUserId((int) tbUser.getUserId());
					System.out.println("tbCar = " + tbCar);
					int num1=carService.addUserCar(tbCar);
					if(num1>0){
						return "true";
					}else {
						return "false";
					}
				}else {
					return "false";
				}
			}
		}
		else {
			return "false";
		}
	}

	//车辆出场记录
	@ResponseBody
    @RequestMapping("queryExit")
    public Object queryExit(int start,int end) throws Exception{
	    List<TbExit> list = carService.queryExit(start, end);
        if(WebSocket.electricSocketMap.get("ip")!=null){
            for (Session session:WebSocket.electricSocketMap.get("ip"))
            {
                session.getBasicRemote().sendText("new,new");
            }

        }
	    return JSON.toJSONString(list);
    }

    //查询车辆信息
	@ResponseBody
	@RequestMapping("queryCarInfo")
	public Object queryCarInfo(String carNum) throws Exception{
		TbCar tbCar = carService.queryCarInfo(carNum);
		if (tbCar!=null){
			TbVip tbVip = vipService.queryVipInfo(tbCar.getVipId());
			tbCar.setTbVip(tbVip);
			return JSON.toJSONString(tbCar);
		}else{
			return "false";
		}
	}
}
