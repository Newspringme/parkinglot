package com.cnzk.controller;

import com.cnzk.pojo.*;
import com.cnzk.service.*;
import com.cnzk.utils.ResponseUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;




@Controller
@RequestMapping("/LoginController")
public class LoginController {
    @Resource
    private MenuService menuService;


//  获取后台用户菜单
@RequestMapping("/findMenu")
@ResponseBody
    public void findMenu(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        Admin admin = (Admin) request.getSession().getAttribute("admin");
//        System.out.println("findMenu-admin="+admin);
        Admin admin = new Admin();
        admin.setAdminName("admin");
        admin.setRoleId(2);
        System.out.println("findMenu-admin="+admin.toString());
        if (null !=admin) {
            Integer rolesid = admin.getRoleId();
            LinkedHashMap<String, List<TbMenu>> menumap= menuService.findMenus(rolesid);
            if (null !=menumap){
                request.getSession().setAttribute("menuMap", menumap);
                ResponseUtils.outHtml(response,"success");
            }else{
                ResponseUtils.outHtml(response,"error");
            }
        }else{
            ResponseUtils.outHtml(response,"error");
        }
    }

}
