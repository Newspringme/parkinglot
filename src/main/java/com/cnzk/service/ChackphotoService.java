package com.cnzk.service;

import com.cnzk.pojo.TbPark;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ChackphotoService
{
	String file(MultipartFile file);//入库车牌确认
	HashMap<String, Object> file2(MultipartFile file2);//出库车牌确认
	String finduser(String carnum);//根据车牌找用户
	String findentertime(String carnum);//根据车牌查最近的入场时间
	void caraddenter(String carnum,String starttime);//插入进场时间
	void caraddexit(String carnum,String starttime);//插入出场时间
	String findcarvip(String carnum);//查权限
	String parkspacemsg(String carnum);//查重复
	List<TbPark> findParkSpace(String state);//查空车位
	String findParkSpacenum(String state);//查空车位数量
	void updatetoPark(TbPark tbPark);
	String carfindps(String carNum);
}
