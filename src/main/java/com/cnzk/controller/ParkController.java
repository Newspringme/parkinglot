package com.cnzk.controller;

import com.alibaba.fastjson.JSON;
import com.cnzk.service.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/parkController")
public class ParkController {
    @Autowired
    private ParkService parkService;

    @ResponseBody
    @RequestMapping(value = "/getParkData")
    public Object getParkData(){
        Map map = new HashMap();
        map.put("put",parkService.queryPark());
        System.out.println(JSON.toJSON(map));
//        return JSON.toJSON(map);
        return JSON.toJSON(map);
    }

}
