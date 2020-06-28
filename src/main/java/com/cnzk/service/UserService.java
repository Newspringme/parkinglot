package com.cnzk.service;

import com.cnzk.pojo.LayuiData;
import com.cnzk.pojo.TbUser;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.Map;

/**
 * @author su
 * @date 2020/6/12-13:46
 */
public interface UserService
{
	void carexit(String carnum);

	//	添加用户
	int addUser(TbUser tbUser);

	//	根据车牌号查询用户
	TbUser queryUserByCarNum(String carNum);

	//	根据用户名查询用户
	TbUser queryUserByUserName(String userName);

	//	查询用户，包括带条件,分页,记录数
	LayuiData queryTbUser(TbUser tbUser, int start, int pageSize);

	//	登录
	String userlogin(String phone);

	String findPhone(String phone);

	String userReg(String phone,String password);

	//通过手机号获取用户
	TbUser queryUser(String userTel);
}
