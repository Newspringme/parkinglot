package com.cnzk.service;

import com.cnzk.mapper.UserMapper;
import com.cnzk.pojo.LayuiData;
import com.cnzk.pojo.TbUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

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
	public LayuiData queryTbUser(TbUser tbUser, int start, int pageSize)
	{
		return null;
	}

	@Override
	public int deleteTbUser(int[] array)
	{
		return 0;
	}

	@Override
	public void carexit(String carnum) {
		userMapper.carexit(carnum);
	}

	@Override
	public String userlogin(String phone)
	{
		String password1=userMapper.userLogin(phone);

		System.out.println("password1--------"+password1);
		if (password1==null){
			System.out.println("未找到当前用户");
			return "未找到";
		}
		return password1;
	}

	@Override
	public String findPhone(String phone)
	{
		String phone1=userMapper.findPhone(phone);
		System.out.println("phone1 userserviceimp"+phone1);
		if (phone1==null){
			return "用户未找到";
		}
		else {
			return "已注册";
		}
	}

	@Override
	public String userReg(String phone, String password)
	{
		userMapper.userReg(phone,password);

		return null;
	}

}
