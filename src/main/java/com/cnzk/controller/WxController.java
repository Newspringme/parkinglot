package com.cnzk.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @author su
 * @date 2020/6/21-23:16
 */
//@RestController
@Controller
public class WxController
{
	@RequestMapping("handleCombo")
	@ResponseBody
	public Object handleCombo(String carNum,String comboId){
		boolean bool=true;
		System.out.println("carNum ========= " + carNum);
		System.out.println("comboId = " + comboId);
		return bool;

	}
}
