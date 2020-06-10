package com.cnzk.controller;

import com.cnzk.service.ChackphotoService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/ChackphotoController")
public class ChackphotoController
{
	@Autowired
	private ChackphotoService chackphotoService;

	@RequestMapping("/File")
	@ResponseBody
	public String file(@RequestParam("file")MultipartFile file){

		String carnum = chackphotoService.file(file);
		System.out.println("carnum:"+carnum);
		SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
		String starttime = sdf.format(new Date());
		//查车找人
		String username=chackphotoService.finduser(carnum);

		chackphotoService.caraddenter(carnum,starttime);

		System.out.println("入场用户-----------"+username);
        String state=null;
        String ps=null;
		//		Object obj = new Gson().toJson()
		return carnum+","+username+","+state+","+starttime+","+ps;

	}

}
