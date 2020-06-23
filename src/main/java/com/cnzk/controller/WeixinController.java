package com.cnzk.controller;

import com.alibaba.fastjson.JSON;
import com.cnzk.pojo.LayuiData;
import com.cnzk.pojo.TbFeedback;
import com.cnzk.pojo.TbBill;
import com.cnzk.service.RoleServeice;
import com.cnzk.service.WeiXinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WeixinController {

    @Autowired
    private WeiXinService weiXinService;
    @ResponseBody
    @RequestMapping("queryImgUrl")
    public Object queryImgUrl(){
        List<String> list =weiXinService.queryImgUrl();
        System.out.println("list = " + JSON.toJSONString(list));
        return list;
    }

    @ResponseBody
    @RequestMapping("sumbitFeedback")
    public Object sumbitFeedback(TbFeedback tbFeedback){
       int i= weiXinService.feedback(tbFeedback);
       if (i!=0){
           System.out.println(tbFeedback.toString());
           return "提交成功";
       }
       return "提交成功";

    }

    @ResponseBody
    @RequestMapping("queryCode")
    public Object queryCode(){

        System.out.println(111111111);
        List list = new ArrayList();
        list.add("222");
        return list;
    }

    //	小程序订单查询
    @ResponseBody
    @RequestMapping("weiXinQueryBill")
    public Object weiXinQueryBill(String carNum){
        LayuiData layuiData=weiXinService.weiXinQueryBill(carNum);
        System.out.println("layuiData = " + JSON.toJSONString(layuiData));
        return layuiData;
    }

    // 根据订单编号查账单信息
    @ResponseBody
    @RequestMapping("queryBilldetails")
    public Object queryBilldetails(String carNum,String billNum){
        TbBill tbBill =weiXinService.queryBilldetails(carNum,billNum);
        System.out.println(tbBill.toString());
        return tbBill;
    }
}
