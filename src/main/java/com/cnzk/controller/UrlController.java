package com.cnzk.controller;

import com.alibaba.fastjson.JSON;
import com.cnzk.pojo.Admin;
import com.cnzk.pojo.LayuiData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/url")
public class UrlController
{
	@RequestMapping("/{path}")
	public String queryRolesList(@PathVariable(value = "path") String path){
		//获取页码
		return path;
	}


}
