package com.cnzk.controller;

import com.alibaba.fastjson.JSON;
import com.cnzk.pojo.Admin;
import com.cnzk.pojo.LayuiData;
import com.cnzk.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author su
 * @date 2020/6/5-15:29
 */
@Controller
public class AdminController
{
	@Autowired
	private AdminService adminService;

	@ResponseBody
	@RequestMapping("queryAdmin")
	public Object queryAdmin(String msg,String page,String limit){
		System.out.println("带条件获取管理员列表");
		Admin admin=new Admin();
		if(msg!=null&&!"".equals(msg.trim())){
			admin= JSON.parseObject(msg,Admin.class);
		}
		int startPage=Integer.parseInt(page);//获取页码;
		int pageSize=Integer.parseInt(limit);//每页数量
		int start = (startPage-1)*pageSize;//计算出起始查询位置
		LayuiData layuiData=adminService.queryAdmin(admin,start,pageSize);
		System.out.println("layuiData = " + JSON.toJSONString(layuiData));
		return layuiData;
	}
}
