package com.cnzk.controller;

import com.cnzk.mapper.UserMapper;
import com.cnzk.pojo.TbCar;
import com.cnzk.websocket.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.websocket.Session;
import java.io.IOException;

/**
 * @author su
 * @date 2020/6/23-0:03
 */
@Controller
public class TimerController
{
	@Resource
	UserMapper userMapper;
	@Scheduled(cron = "0/3 * * * * *")//每隔两秒执行一次
//	@Scheduled(cron = "0 0 10 * * *")//每天早上10点执行一次
	public void runTimer() throws IOException
	{
		TbCar tbCar=userMapper.queryEndTime();
		System.out.println("月缴到期tbCar ===== " + tbCar);
		if(tbCar!=null){
			if(WebSocket.electricSocketMap.get("ip")!=null){
				for (Session session:WebSocket.electricSocketMap.get("ip"))
				{
					session.getBasicRemote().sendText(tbCar.getCarNum()+"的月缴产品还剩"+5+"天到期");
				}

			}
		}
	}
}
