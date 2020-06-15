package com.cnzk.mapper;

import com.cnzk.pojo.TbEnter;
import com.cnzk.pojo.TbPark;
import com.cnzk.pojo.TbUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.server.PathParam;
import java.util.List;

@Mapper
public interface UserMapper
{
	String carfinduser(@PathParam("carnum") String carnum);
	void caraddenter(@PathParam("carnum") String carnum,@PathParam("starttime") String starttime);
	String findcarvip(@PathParam("carnum") String carnum);
	void caraddexit(@PathParam("carnum") String carnum,@PathParam("exittime") String exittime);
	String carfindentertime(@PathParam("carnum") String carnum);
	String parkspacemsg(@PathParam("carnum") String carnum);
	List<TbPark> findParkSpace(@PathParam("state")String state);
	String findParkSpacenum(@PathParam("state")String state);
	void updatatoPark(@PathParam("tbPark")TbPark tbPark);
	String carfindps(@PathParam("carNum") String carNum);

}
