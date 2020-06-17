package com.cnzk.controller;

import com.cnzk.pojo.*;
import com.cnzk.service.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;


/**
 * @author LQ
 */
@Controller
@RequestMapping("/LoginController")
public class LoginController {
    @Resource
    private MenuService menuService;

    @RequestMapping("/Menu")
    @ResponseBody
    public Map<String, Object> menu(HttpServletRequest request, HttpServletResponse response) {
        Admin admin = (Admin) request.getSession().getAttribute("tbAdmin");
        System.out.println("admin-------------"+admin.toString());
        return menuService.menu(admin.getRoleId());
    }

}
