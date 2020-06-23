package com.cnzk.controller;
import com.alibaba.fastjson.JSON;
import com.cnzk.aoplog.Log;
import com.cnzk.pojo.LayuiData;
import com.cnzk.pojo.TbUser;
import com.cnzk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.Map;

/**
 * @author su
 * @date 2020/6/12-13:45
 */
@Controller
@RequestMapping("UserController")
public class UserController
{
	@Autowired
	private UserService userService;
	//登陆l
	@RequestMapping(value = "/userLogin", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String adminLogin( @PathParam("phone") String phone, @PathParam("password") String password)
	{
		System.out.println("===============================用户登陆=============================");
		System.out.println("password=="+phone+"==="+password);
		String phone1=phone;
		String pw1=userService.userlogin(phone1);
		if (pw1.equals("未找到")){
			System.out.println("请先注册");
			return "请先注册";
		}
		String pw2=password;
		System.out.println("pw1-------"+pw1+"pw2-----"+pw2);
		if (pw1.equals(pw2)){
			return "登陆成功";
		}
		return "用户名或密码错误";

	}
	@RequestMapping(value = "/userAdd", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String addLogin( @PathParam("phone") String phone, @PathParam("password") String password)
	{
		System.out.println("===============================用户注册=============================");
		System.out.println("password=="+phone+"==="+password);
		String findPhone = userService.findPhone(phone);
		if (findPhone.equals("用户未找到")){
            userService.userReg(phone,password);
			System.out.println("插入用户数据 手机号:"+phone+"密码:"+password);
			return "注册成功";
		}
		else{
			return "手机号已注册";
		}

	}



//	添加用户
	@RequestMapping("addUser")
	@ResponseBody
	public Object addUser(String msg){
		boolean bool=false;
		if(msg!=null&&!"".equals(msg.trim())){
			TbUser tbUser= JSON.parseObject(msg,TbUser.class);
			int num=userService.addUser(tbUser);
			if(num>0){
				bool=true;
				System.out.println("用户添加成功");
			}
		}
		return bool;
	}
	//获取用户列表
	@ResponseBody
	@RequestMapping("queryTbUser")
	public Object queryTbUser(String msg, String page, String limit)
	{
		TbUser tbUser = new TbUser();
		if (msg != null && !"".equals(msg.trim()))
		{
			tbUser = JSON.parseObject(msg, TbUser.class);
		}
		//获取页码;
		int startPage = Integer.parseInt(page);
		//每页数量
		int pageSize = Integer.parseInt(limit);
		//计算出起始查询位置
		int start = (startPage - 1) * pageSize;
		LayuiData layuiData = userService.queryTbUser(tbUser, start, pageSize);
		System.out.println("layuiData = " + JSON.toJSONString(layuiData));
		return layuiData;
	}
	//	删除用户
	@RequestMapping("/deleteTbUser")
	@ResponseBody
	@Log(operationThing = "删除用户", operationType = "删除")
	public Object deleteTbUser(String delList, String userId)
	{
		int num = 0;
		boolean bool = false;
		if (userId != null && !"".equals(userId.trim()))
		{
			System.out.println("删除用户userId = " + userId);
			int[] arr = {Integer.parseInt(userId)};
			num = userService.deleteTbUser(arr);
		}
		else
		{
			System.out.println("delList = " + delList);
			String[] strArr = delList.split(",");
			int[] intArr = new int[strArr.length];
			for (int i = 0; i < strArr.length; i++)
			{
				intArr[i] = Integer.parseInt(strArr[i]);
			}
			num = userService.deleteTbUser(intArr);
		}
		if (num > 0)
		{
			System.out.println("删除成功");
			bool = true;
		}
		return bool;
	}

}
