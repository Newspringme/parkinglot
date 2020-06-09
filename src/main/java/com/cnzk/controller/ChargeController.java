package com.cnzk.controller;

import com.alibaba.fastjson.JSON;
import com.cnzk.aoplog.Log;
import com.cnzk.pojo.Charge;
import com.cnzk.pojo.LayuiData;
import com.cnzk.service.ChargeService;
import com.cnzk.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/ChargeController")
public class ChargeController {
    @Autowired
    private ChargeService chargeService;

    @ResponseBody
    @RequestMapping(value = "/chargelogin",produces = { "application/json;charset=UTF-8"})
    public String ChargeLogin(@RequestParam Map<String,Object> param, HttpSession session)  {
        System.out.println("===============================收费人员登陆=============================");
        String vcode = session.getAttribute("vcode").toString();//获取session上的验证码
        System.out.println("验证码："+vcode);
        System.out.println(param);
        if(vcode.equalsIgnoreCase(param.get("chargevcode").toString())){
            return chargeService.chargelogin(param,session);//获取service层返回的信息
        }
        return "验证码错误";
    }
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

    @ResponseBody
    @RequestMapping("updateState")
    @Log(operationThing = "更新收费员状态",operationType = "更新")
    public void updateState(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("doPost");

        Charge charge = new Charge();

        charge.setAdminName(request.getParameter("adminName"));
        charge.setAdminState(request.getParameter("adminState"));

        int i = chargeService.updateState(charge);
        if (i>0){
            response.getWriter().write("true");
        }else{
            response.getWriter().write("false");
        }
    }

    @ResponseBody
    @RequestMapping("insertNewCharge")
    @Log(operationThing = "添加新收费员",operationType = "添加")
    public Object insertNewCharge(String msg){
        System.out.println("添加收费员");
        Charge charge = JSON.parseObject(msg,Charge.class);
        //MD5加密
        charge.setAdminPass(MD5.machining(charge.getAdminPass()));
        System.out.println(JSON.toJSONString(charge));
        Integer result = chargeService.searchCharge(charge.getAdminName());
        if (result != null ){
            return "账号已存在";
        }else {
            int i = chargeService.insertNewCharge(charge);
            if (i>0){
                return "true";
            }else{
                return "false";
            }
        }
    }

    @ResponseBody
    @RequestMapping("logOff")
    @Log(operationThing = "收费员离职",operationType = "删除")
    public Object logOff(HttpServletRequest request){
        System.out.println("收费员离职");
        int i = chargeService.logOff(request.getParameter("adminName"));
        if (i>0){
            return "true";
        }else{
            return "false";
        }
    }

    @ResponseBody
    @RequestMapping("resetPass")
    @Log(operationThing = "重置收费员密码",operationType = "更新")
    public Object insertNewCharge(HttpServletRequest request){
        System.out.println("重置密码");
        int i = chargeService.resetPass(request.getParameter("adminName"));
        if (i>0){
            return "true";
        }else{
            return "false";
        }
    }
}
