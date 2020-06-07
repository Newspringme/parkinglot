package com.cnzk.controller;

import com.alibaba.fastjson.JSON;
import com.cnzk.pojo.LayuiData;
import com.cnzk.pojo.TbRole;
import com.cnzk.service.RoleServeice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @Autowired
    private RoleServeice roleService;

    @ResponseBody
    @RequestMapping("queryRole")
    public Object queryRole(String page,String limit){
        int startPage=Integer.parseInt(page);//获取页码;
        int pageSize=Integer.parseInt(limit);//每页数量
        int start = (startPage-1)*pageSize;//计算出起始查询位置
        LayuiData layuiData=roleService.queryRole(start,pageSize);
        System.out.println("layuiData = " + JSON.toJSONString(layuiData));
        return layuiData;
    }

    @ResponseBody
    @RequestMapping("addRole")
    public Object addRole(String roleName){
        roleService.addRole(roleName);
        return "true";
    }


    @ResponseBody
    @RequestMapping("deleteRole")
    public Object deleteRole(TbRole role){
        roleService.delecteRole(role);
        return "true";
    }
    @ResponseBody
    @RequestMapping("editRole")
    public Object editRole(TbRole role){
        roleService.editRole(role);
        return "true";
    }

}
