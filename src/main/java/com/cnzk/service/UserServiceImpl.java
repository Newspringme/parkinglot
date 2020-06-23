package com.cnzk.service;

import com.cnzk.mapper.UserMapper;
import com.cnzk.pojo.LayuiData;
import com.cnzk.pojo.TbUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
		return userMapper.addUser(tbUser);
	}

	//根据车牌号查询用户
	@Override
	public TbUser queryUserByCarNum(String carNum)
	{
		return userMapper.queryUserByCarNum(carNum);
	}

	//根据用户名查询用户
	@Override
	public TbUser queryUserByUserName(String userName)
	{
		return userMapper.queryUserByUserName(userName);
	}

	@Override
	public void carexit(String carnum)
	{
	}


	//	查询用户，包括带条件,分页,记录数
	@Override
	public LayuiData queryTbUser(TbUser tbUser, int start, int pageSize)
	{
		List<TbUser> list = userMapper.queryTbUser(tbUser, start, pageSize);
		int count = userMapper.queryTbUserCount(tbUser);
		LayuiData layuiData = new LayuiData();
		layuiData.setCode(0);
		layuiData.setCount(count);
		layuiData.setData(list);
		return layuiData;
	}
}
