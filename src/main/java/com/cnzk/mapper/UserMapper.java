package com.cnzk.mapper;

import com.cnzk.pojo.TbEnter;
import com.cnzk.pojo.TbUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.server.PathParam;

@Mapper
public interface UserMapper
{
	String carfinduser(@PathParam("carnum") String carnum);
	void caraddenter(@PathParam("carnum") String carnum,@PathParam("starttime") String starttime);
	String findcarvip(@PathParam("carnum") String carnum);
}
