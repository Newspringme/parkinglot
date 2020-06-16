package com.cnzk.service;

import com.cnzk.mapper.UserMapper;
import com.cnzk.pojo.TbUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author su
 * @date 2020/6/12-13:46
 */
@Service
public class UserServiceImpl implements UserService
{
	@Resource
	private UserMapper userMapper;

	//添加用户
	@Override
	public int addUser(TbUser tbUser)
	{
		return 0;
	}

	//根据车牌号查询用户
	@Override
	public TbUser queryUserByCarNum(String carNum)
	{
		return null;
	}
	//根据用户名查询用户
	@Override
	public TbUser queryUserByUserName(String userName)
	{
		return userMapper.queryUserByUserName(userName);
	}

	@Override
	public void carexit(String carnum) {
		userMapper.carexit(carnum);
	}
}
