package com.cnzk.service;

import com.cnzk.pojo.TbUser;

/**
 * @author su
 * @date 2020/6/12-13:46
 */
public interface UserService
{
	//	添加用户
	int addUser(TbUser tbUser);

	//	根据车牌号查询用户
	TbUser queryUserByCarNum(String carNum);

	//	根据用户名查询用户
	TbUser queryUserByUserName(String userName);
}
