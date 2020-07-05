package com.cnzk.mapper;

import com.cnzk.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.bouncycastle.crypto.paddings.TBCPadding;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.server.PathParam;
import java.util.List;

@Mapper
public interface UserMapper
{
	String carfinduser(@PathParam("carnum") String carnum);

	void caraddenter(@PathParam("carnum") String carnum, @PathParam("starttime") String starttime);

	String findcarvip(@PathParam("carnum") String carnum);

	void caraddexit(@PathParam("carnum") String carnum, @PathParam("exittime") String exittime);

	void carexit(@PathParam("carnum") String carnum);

	String carfindentertime(@PathParam("carnum") String carnum);

	String parkspacemsg(@PathParam("carnum") String carnum);

	List<TbPark> findParkSpace(@PathParam("state") String state);

	String findParkSpacenum(@PathParam("state") String state);

	void updatatoPark(@PathParam("tbPark") TbPark tbPark);

	String carfindps(@PathParam("carNum") String carNum);

	void addimgurl(@PathParam("carnum") String carnum, @PathParam("url") String url);

	//	添加用户
	int addUser(TbUser tbUser);

	//	根据车牌号查询用户
	TbUser queryUserByCarNum(String carNum);

	//	根据用户名查询用户
	TbUser queryUserByUserName(String userName);

	//	查询用户记录数
	int queryTbUserCount(TbUser tbUser);

	//	查询用户，包括带条件及分页
	List<TbUser> queryTbUser(@Param("tbUser") TbUser tbUser, @Param("start") int start, @Param("pageSize") int pageSize);

	//	删除用户
	int deleteTbUser(int[] array);

	int updataUser(TbUser tbUser);

	String userLogin(String phone);

	String findPhone(String phone);

	void userReg(String phone, String password);

	//  获取月缴要到期的车辆
	List<TbCar> queryEndTime();

	//	更改过期套餐
	int updateCombo(TbCar tbCar);

	//通过手机号获取用户
	TbUser queryUser(@Param("userCard") String userCard);


	TbUser queryOpenIdUser(@Param("userCard") String open_id);

	Integer uploadLoginTime(TbUser user);

	Integer insertUserInfo(TbUser user);

	Integer addVehicle(@Param("carNum") String carNum);

	Long queryVehicle(@Param("carNum") String carNum);

	Integer addVehicleforuser(@Param("carId") Long carId, @Param("userId") String userId);


}