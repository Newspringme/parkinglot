package com.cnzk.controller;

import com.alibaba.fastjson.JSON;
import com.cnzk.aoplog.Log;
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
    @Log(operationThing = "添加角色",operationType = "添加")
    public Object addRole(TbRole role){
        if (roleService.addRole(role)!=0){
            System.out.println("添加成功");
        return "true";
    }else {
            System.out.println("删除失败");
        return "false";
    }

}


    @ResponseBody
    @RequestMapping("deleteRole")
    @Log(operationThing = "删除角色",operationType = "删除")
    public Object deleteRole(TbRole role){
        if (roleService.delecteRole(role)!=0){
            System.out.println("删除成功");
            return "true";
        }else {
            System.out.println("删除失败");
            return "false";
        }

    }
    @ResponseBody
    @RequestMapping("editRole")
    @Log(operationThing = "修改角色",operationType = "修改")
    public Object editRole(TbRole role){
        if (roleService.editRole(role)!=0){
            System.out.println("修改成功");
            return "true";
        }else {
            System.out.println("修改失败");
            return "false";
        }
    }

}
