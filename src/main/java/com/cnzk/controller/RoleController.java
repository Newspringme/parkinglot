package com.cnzk.controller;

import com.alibaba.fastjson.JSON;
import com.cnzk.pojo.LayuiData;
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
    public Object queryAdmin(String msg,String page,String limit){
        System.out.println("获取角色列表");
        int startPage=1;//获取页码;
        int pageSize=5;//每页数量
        int start = (startPage-1)*pageSize;//计算出起始查询位置
        LayuiData layuiData=roleService.queryRole(start,pageSize);
        System.out.println("layuiData = " + JSON.toJSONString(layuiData));
        return layuiData;
    }
}
