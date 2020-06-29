package com.cnzk.controller;

import com.alibaba.fastjson.JSON;
import com.cnzk.pojo.*;
import com.cnzk.service.RoleServeice;
import com.cnzk.service.WeiXinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
public class WeixinController {

    @Resource
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
    @RequestMapping("sumbitUserdata")
    public Object sumbitUserdata(TbUser TbUser){
        int i= weiXinService.UpdateUser(TbUser);
        if (i!=0){
            System.out.println(TbUser.toString());
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
    public Object weiXinQueryBill(String carList){
        System.out.println(carList);
        List<TbCar> tbCarList = JSON.parseArray(carList,TbCar.class);
        System.out.println(tbCarList);
        LayuiData layuiData=weiXinService.weiXinQueryBill(tbCarList);
        System.out.println("layuiData = " + JSON.toJSONString(layuiData));
        return layuiData;
    }

    // 根据订单编号查账单信息
    @ResponseBody
    @RequestMapping("queryBilldetails")
    public Object queryBilldetails(String billNum){
        TbBill tbBill =weiXinService.queryBilldetails(billNum);
        System.out.println(tbBill.toString());
        return tbBill;
    }

    // 查看空车位
    @ResponseBody
    @RequestMapping("queryNullPark")
    public Object queryNullPark(){
        Integer num =weiXinService.queryNullPark();
        System.out.println(num);
        return num;
    }


    // 根据用户标识查车牌
    @ResponseBody
    @RequestMapping("queryCarNum")
    public Object queryCarNum(String userCard){
        System.out.println("queryCarNum:"+userCard);
        List<TbCar> tbCarList =weiXinService.queryCarNum(userCard);
        System.out.println(tbCarList);
        return tbCarList;
    }

    // 根据手机号查用户
    @ResponseBody
    @RequestMapping("queryUserbyUserTel")
    public Object queryUser(String userTel){
        TbUser tbUser =weiXinService.queryUser(userTel);
        System.out.println(tbUser);
        return tbUser;
    }

    //微信登录
    @ResponseBody
    @RequestMapping("weChatLogin")
    public Object weChatLogin(String code,String userHead, String userName, String userGender, String userCity,String userProvince) {
        System.out.println("????????????????");
        ResultData resultData = weiXinService.weChatLogin(code, userHead, userName, userGender);
        return JSON.toJSONString(resultData);
    }

}
