package com.cnzk.controller;

import com.cnzk.pojo.TbCombo;
import com.cnzk.pojo.TbPark;
import com.cnzk.pojo.TbRates;
import com.cnzk.service.AdminService;
import com.cnzk.service.ChackphotoService;
import com.cnzk.utils.ChangeBase64;
import com.cnzk.utils.PriceUtils;
import com.cnzk.websocket.WebSocket;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import static org.apache.tomcat.util.codec.binary.Base64.encodeBase64;

@Controller
@RequestMapping("/ChackphotoController")
public class ChackphotoController
{
	@Autowired
	private ChackphotoService chackphotoService;
	@Resource
	private AdminService adminService;

	@RequestMapping("/File")
	@ResponseBody
	public String file(@RequestParam("file")MultipartFile file){

		List<TbPark> PS=chackphotoService.findParkSpace("未停车");//查剩余空车位
		if (PS.size() == 0) {//无车位
			return "NO";
		}else{



		String carNum = chackphotoService.file(file);
		System.out.println("车牌-----------:"+carNum);
        String ParkSpace=chackphotoService.parkspacemsg(carNum);//查重
		if(carNum=="NO")
		{
			return "NOCAR";//无法识别车牌
		}else if(ParkSpace!=null){
			return  "HAVEING";//车牌重复
		}

		SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
		String starttime = sdf.format(new Date());
		//查车找人
		String username=chackphotoService.finduser(carNum);
		System.out.println("入场用户-----------"+username);
        //入场插入数据
		chackphotoService.caraddenter(carNum,starttime);
		String state;
		//车辆情况查询 是临时还是有身份
		if ("临时用户".equals(username)){
			state = "临时车";
		} else {
			state=chackphotoService.findcarvip(carNum);
		}
		System.out.println("车辆情况---------"+state);
		//车位查询
		String ps=chackphotoService.findParkSpacenum("未停车");
		//插入车库数据
			Random r = new Random();
			int num = r.nextInt(PS.size());
			System.out.println("num==============="+num);
			System.out.println("--------------------"+PS.get(num).toString());
			long parkId = PS.get(num).getParkId();
			String parkState="已停车";
			TbPark tbPark= new TbPark(parkId,parkState,carNum);
			System.out.println("tbPark+++++++++++++"+tbPark);
			chackphotoService.updatetoPark(tbPark);


		//		Object obj = new Gson().toJson()
		return carNum+","+username+","+state+","+starttime+","+ps;
		}

	}

	@RequestMapping("/File2")
	@ResponseBody
	public String file2(@RequestParam("file2")MultipartFile file2,HttpServletRequest request) throws Exception{
		String fileName = file2.getOriginalFilename();  //获取文件名
		String path = request.getSession().getServletContext().getRealPath("/upload");
		String fileTyle = fileName.substring(fileName.lastIndexOf("."));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");//设置日期格式
		String timeDate = df.format(new Date());
		String url = path+"/"+timeDate+fileTyle;

		String carNum = chackphotoService.file(file2);
		System.out.println("车牌-----------:"+carNum);
		System.out.println("url------------"+url);

		String change64=chackphotoService.change64(file2);
		String img_str = change64;
		System.out.println("img_str"+img_str);

		file2.transferTo(new File(url));

//		String change64= ChangeBase64.multipartFileToBASE64(file2);

//		HashMap<String,Object> condition = chackphotoService.file2(file2);

//		String img_str = condition.get("change64").toString();
//		String carNum = condition.get("carNum").toString();

		chackphotoService.addimgurl(carNum,url);
		SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
		String exittime = sdf.format(new Date());//查车找人

		String username=chackphotoService.finduser(carNum);
		System.out.println("入场用户-----------"+username);
		//出场插入数据
		chackphotoService.caraddexit(carNum,exittime);
		//车辆情况查询 是临时还是有身份
		String state;
		if ("临时用户".equals(username)){
			state = "临时车";
		} else {
			state=chackphotoService.findcarvip(carNum);
		}
		System.out.println("车辆情况---------"+state);
		//查询入场时间
		String entertime=chackphotoService.findentertime(carNum);
		System.out.println("查询入场时间"+entertime);

//		查询计费规则
		TbRates tbRates = adminService.queryPrice();
		Map map = new HashMap();
		try
		{
			map = PriceUtils.getBill(entertime,exittime,tbRates);
		} catch (ParseException e)
		{
			e.printStackTrace();
		}

		//车位查询
		String ps=chackphotoService.carfindps(carNum);
		Double money= Double.valueOf(map.get("bill")+"");
		String time=map.get("time").toString();;
//		String money=null;
//		String time=null;
		String date= carNum+","+username+","+state+","+ps+","+entertime+","+exittime+","+time+","+money+","+img_str;
		System.out.println("车牌："+carNum+"用户名："+username+"车状态："+state+"车位："+ps+"进场时间："+entertime+"出场时间："+exittime+"总时长："+time+"收费："+money+"图片："+img_str);
		//		Object obj = new Gson().toJson()
		if(WebSocket.electricSocketMap.get("ip")!=null){
			for (Session session:WebSocket.electricSocketMap.get("ip"))
			{
				session.getBasicRemote().sendText("success,"+date);
			}

		}
		return date;

	}

	@RequestMapping("/search")
	@ResponseBody
	public String search(String carnum){
		String ParkSpace=chackphotoService.parkspacemsg(carnum);//查重
		if(ParkSpace==null){
			return  "HAVEING";
		}

		SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
		String exittime = sdf.format(new Date());//查车找人

		String username=chackphotoService.finduser(carnum);
		System.out.println("入场用户-----------"+username);
		//出场插入数据
		//车辆情况查询 是临时还是有身份
		String state;
		if ("临时用户".equals(username)){
			state = "临时车";
		} else {
			state=chackphotoService.findcarvip(carnum);
		}
		System.out.println("车辆情况---------"+state);
		//查询入场时间
		String entertime=chackphotoService.findentertime(carnum);
		System.out.println("查询入场时间"+entertime);

		if (entertime==null){
			return  "HAVEING";
		}
//		查询计费规则
		TbRates tbRates = adminService.queryPrice();
		Map map = new HashMap();
		try
		{
			map = PriceUtils.getBill(entertime,exittime,tbRates);
		} catch (ParseException e)
		{
			e.printStackTrace();
		}

		//车位查询
		String ps=chackphotoService.carfindps(carnum);
		Double money= Double.valueOf(map.get("bill")+"");
		String time=map.get("time").toString();;
		String date= carnum+","+username+","+state+","+ps+","+entertime+","+exittime+","+time+","+money;
		System.out.println("车牌："+carnum+"用户名："+username+"车状态："+state+"车位："+ps+"进场时间："+entertime+"出场时间："+exittime+"总时长："+time+"收费："+money);
		//		Object obj = new Gson().toJson()
		return date;

	}



}
