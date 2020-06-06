package com.cnzk.service;

import com.cnzk.pojo.Admin;
import com.cnzk.pojo.LayuiData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author su
 * @date 2020/6/5-15:30
 */
public interface AdminService
{
	//	查询管理员，包括带条件,分页,记录数
	LayuiData queryAdmin(Admin admin, int start, int pageSize);
}
