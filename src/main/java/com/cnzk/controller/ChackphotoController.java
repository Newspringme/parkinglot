package com.cnzk.controller;

import com.cnzk.service.ChackphotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/ChackphotoController")
public class ChackphotoController
{
	@Autowired
	private ChackphotoService chackphotoService;

	@RequestMapping("/File")
	@ResponseBody
	public String file(@RequestParam("file")MultipartFile file){


		return chackphotoService.file(file);

	}

}
