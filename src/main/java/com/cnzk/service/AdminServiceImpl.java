package com.cnzk.service;

import com.cnzk.mapper.AdminMapper;
import com.cnzk.mapper.ComboMapper;
import com.cnzk.mapper.RatesMapper;
import com.cnzk.pojo.Admin;
import com.cnzk.pojo.LayuiData;
import com.cnzk.pojo.TbCombo;
import com.cnzk.pojo.TbRates;
import com.cnzk.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;


/**
 * @author su
 * @date 2020/6/5-15:31
 */
@Service
public class AdminServiceImpl implements AdminService
{
	@Resource
	private AdminMapper adminMapper;
	@Resource
	private RatesMapper ratesMapper;
	@Resource
	private ComboMapper comboMapper;
    //登陆
	/**
	 * 将登陆账号密码拿去数据库验证
	 * @param map
	 * @param session
	 * @return
	 */
	@Override

	public String adminlogin(Map<String,Object> map, HttpSession session) {
		map.put("adminpass",MD5.machining(map.get("adminpass").toString()));//将管理员输入的密码转成MD5加密
		System.out.println(map);
		Admin tbAdmin2 = adminMapper.adminlogin(map);
		if(tbAdmin2 != null){
			if (tbAdmin2.getRoleId()==3){
				return "您的权限不足，请返回收费端登陆";
			}
			if("启用".equals(tbAdmin2.getAdminState())){
				session.setAttribute("tbAdmin",tbAdmin2);//将管理员信息放到session
				return "success";
			}
			return "您已被禁止登陆！";
		}
		return "账号或密码错误";
	}



	//	查询管理员，包括带条件,分页,记录数
	@Override
	public LayuiData queryAdmin(Admin admin, int start, int pageSize)
	{
		List<Admin> list=adminMapper.queryAdmin(admin, start, pageSize);
		int count=adminMapper.queryAdminCount(admin);
		LayuiData layuiData=new LayuiData();
		layuiData.setCode(0);
		layuiData.setCount(count);
		layuiData.setData(list);
		return layuiData;
	}


	//查计费规则列表
	@Override
	public LayuiData queryRatesList(HashMap<String, Object> condition) {
		List<TbRates> tbRoleList = ratesMapper.queryRatesList(condition);
		int count = ratesMapper.queryCount(condition);
		LayuiData layuiData=new LayuiData();
		layuiData.setCode(0);
		layuiData.setCount(count);
		layuiData.setData(tbRoleList);
		return layuiData;
	}

	//查月缴产品列表
	@Override
	public LayuiData queryComboList(HashMap<String, Object> condition) {
		List<TbCombo> tbRoleList = comboMapper.queryComboList(condition);
		int count = comboMapper.queryCount(condition);
		LayuiData layuiData=new LayuiData();
		layuiData.setCode(0);
		layuiData.setCount(count);
		layuiData.setData(tbRoleList);
		return layuiData;
	}
}
