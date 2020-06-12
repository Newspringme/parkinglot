package com.cnzk.service;

import org.springframework.web.multipart.MultipartFile;

public interface ChackphotoService
{
	String file(MultipartFile file);//入库车牌确认
	String file2(MultipartFile file2);//出库车牌确认
	String finduser(String carnum);//根据车牌找用户
	String findentertime(String carnum);//根据车牌查最近的入场时间
	void caraddenter(String carnum,String starttime);//插入进场时间
	void caraddexit(String carnum,String starttime);//插入出场时间
	String findcarvip(String carnum);

}
