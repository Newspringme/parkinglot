package com.cnzk.service;

import com.cnzk.mapper.AdminMapper;
import com.cnzk.pojo.Admin;
import com.cnzk.pojo.LayuiData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author su
 * @date 2020/6/5-15:31
 */
@Service
public class AdminServiceImpl implements AdminService
{
	@Resource
	private AdminMapper adminMapper;

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
}
