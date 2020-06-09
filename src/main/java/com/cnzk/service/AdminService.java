package com.cnzk.service;

import com.cnzk.pojo.Admin;
import com.cnzk.pojo.LayuiData;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author su
 * @date 2020/6/5-15:30
 */
public interface AdminService
{
	//	登录
	String adminlogin(Map<String,Object> map, HttpSession session);

	//	查询管理员，包括带条件,分页,记录数
	LayuiData queryAdmin(Admin admin, int start, int pageSize);

	//查计费规则列表
	LayuiData queryRatesList(HashMap<String, Object> condition);

	//查月缴产品列表
	LayuiData queryComboList(HashMap<String, Object> condition);

}
