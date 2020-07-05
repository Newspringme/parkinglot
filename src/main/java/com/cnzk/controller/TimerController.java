package com.cnzk.controller;

import com.cnzk.mapper.UserMapper;
import com.cnzk.pojo.TbCar;
import com.cnzk.websocket.WebSocket;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.websocket.Session;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author su
 * @date 2020/6/23-0:03
 */
@Controller
public class TimerController
{
	@Resource
	UserMapper userMapper;

		@Scheduled(cron = "0/3 * * * * *")//每隔3秒执行一次
//	@Scheduled(cron = "0 0 0 * * *")//每天0点执行一次
	public void runTimer() throws IOException, ParseException
	{
		List<TbCar> list = userMapper.queryEndTime();
		System.out.println("月缴到期list ===== " + list.size());
		if (list.size() > 0)
		{
			for (TbCar tbCar : list)
			{
				SimpleDateFormat sd = new SimpleDateFormat("yy-MM-dd");
				int days = (int) ((sd.parse(tbCar.getEndTime()).getTime() - sd.parse(sd.format(new Date())).getTime()) / (1000 * 3600 * 24));
				System.out.println("days = " + days);
				if (WebSocket.electricSocketMap.get("ip") != null)
				{
					for (Session session : WebSocket.electricSocketMap.get("ip"))
					{
						if (days >= 0)
						{
							session.getBasicRemote().sendText(tbCar.getCarNum() + "的月缴产品还剩" + days + "天到期");
						}
						else
						{
							System.out.println("-days = " + days);
							//更改过期套餐
							int num=userMapper.updateCombo(tbCar);
							System.out.println("num ===== " + num);
							session.getBasicRemote().sendText(tbCar.getCarNum() + "未办理月缴产品或已过期，请自行选择办理");
						}
					}
				}
			}

		}
	}
}
