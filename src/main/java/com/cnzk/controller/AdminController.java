package com.cnzk.controller;

import com.alibaba.fastjson.JSON;
import com.cnzk.pojo.Admin;
import com.cnzk.pojo.LayuiData;
import com.cnzk.pojo.TreeData;
import com.cnzk.service.AdminService;
import com.cnzk.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/AdminController")
public class AdminController
{
	@Autowired
	private AdminService adminService;

	@Resource
	private AuthorityService authorityService;

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


	//查角色列表
	@ResponseBody
	@RequestMapping("queryRolesList")
	public Object queryRolesList(String page,String limit){
        //获取页码
		int startPage=Integer.parseInt(page);
        //每页数量
		int pageSize=Integer.parseInt(limit);
        //计算出起始查询位置
		int start = (startPage-1)*pageSize;

		Admin admin = new Admin();
		admin.setAdminName("admin");
		admin.setRoleId(2);
		System.out.println("findRolesList-admin="+admin.toString());
		Integer roleId = admin.getRoleId();

		//存带有值得条件
		HashMap<String, Object> condition = new HashMap<>();
		condition.put("roleId",roleId);
		condition.put("start",start);
		condition.put("pageSize",pageSize);
		System.out.println(condition);

		LayuiData layuiData=authorityService.queryRolesList(condition);
		System.out.println("layuiData = " + JSON.toJSONString(layuiData));
		return layuiData;
	}

}
