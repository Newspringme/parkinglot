package com.cnzk.mapper;

import com.cnzk.pojo.Admin;
import com.cnzk.pojo.Charge;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FaceMapper
{


	//通过管理员账户,查询管理员信息
	public Admin findAdminByAccount(String adminnum);



	//通过收费员账户,查询收费员信息
	public Charge findChargeByAccount(String adminnum);


}
