package com.cnzk.controller;

import com.alibaba.fastjson.JSON;
import com.cnzk.pojo.LayuiData;
import com.cnzk.service.LogService;
import com.cnzk.service.RoleServeice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LogController {

    @Autowired
    private LogService logService;

    @ResponseBody
    @RequestMapping("queryLog")
    public Object queryLog(String page,String limit,String username ,String startTime,String endTime){
        LayuiData layuiData=logService.queryLog(page,limit,username,startTime,endTime);
        System.out.println("layuiData = " + JSON.toJSONString(layuiData));
        return layuiData;
    }
}
