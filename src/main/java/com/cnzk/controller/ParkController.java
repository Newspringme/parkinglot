package com.cnzk.controller;

import com.alibaba.fastjson.JSON;
import com.cnzk.pojo.LayuiData;
import com.cnzk.pojo.TbPark;
import com.cnzk.service.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        return JSON.toJSON(map);
    }

    //场内车辆信息
    @ResponseBody
    @RequestMapping("/showParkInfo")
    public Object showParkInfo(String msg,String page,String limit) {
        TbPark tbPark = new TbPark();
        if(msg!=null&&!"".equals(msg.trim())){
            tbPark= JSON.parseObject(msg,TbPark.class);
        }
        int startPage=Integer.parseInt(page);//获取页码;
        int pageSize=Integer.parseInt(limit);//每页数量
        int start = (startPage-1)*pageSize;//计算出起始查询位置
        LayuiData layuiData = parkService.getParkList(tbPark,start,pageSize);
        System.out.println("layuiData = " + JSON.toJSONString(layuiData));
        return layuiData;
    }

}
