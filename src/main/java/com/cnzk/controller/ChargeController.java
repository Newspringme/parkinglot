package com.cnzk.controller;

import com.alibaba.fastjson.JSON;
import com.cnzk.pojo.Charge;
import com.cnzk.pojo.LayuiData;
import com.cnzk.service.ChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChargeController {
    @Autowired
    private ChargeService chargeService;

    @ResponseBody
    @RequestMapping("getChargeList")
    public Object queryCharge(String msg,String page,String limit){
        System.out.println("带条件获取收费员列表");
        Charge charge = new Charge();
        if(msg!=null&&!"".equals(msg.trim())){
            charge= JSON.parseObject(msg,Charge.class);
        }
        int startPage=Integer.parseInt(page);//获取页码;
        int pageSize=Integer.parseInt(limit);//每页数量
        int start = (startPage-1)*pageSize;//计算出起始查询位置
        LayuiData layuiData=chargeService.queryCharge(charge,start,pageSize);
        System.out.println("layuiData = " + JSON.toJSONString(layuiData));
        return layuiData;
    }
}
