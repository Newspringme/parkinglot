package com.cnzk.controller;

import com.alibaba.fastjson.JSON;
import com.cnzk.pojo.LayuiData;
import com.cnzk.service.LogService;
import com.cnzk.service.RoleServeice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    @Autowired
    private LogService logService;

    @ResponseBody
    @RequestMapping("queryLog")
    public Object queryLog(String page,String limit){
        int startPage=Integer.parseInt(page);//获取页码;
        int pageSize=Integer.parseInt(limit);//每页数量
        int start = (startPage-1)*pageSize;//计算出起始查询位置
        LayuiData layuiData=logService.queryLog(start,pageSize);
        System.out.println("layuiData = " + JSON.toJSONString(layuiData));
        return layuiData;
    }
}
