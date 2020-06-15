package com.cnzk.controller;

import com.cnzk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author su
 * @date 2020/6/12-13:45
 */
@Controller
@RequestMapping("UserController")
public class UserController
{
	@Autowired
	private UserService userService;

}
