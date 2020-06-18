package com.cnzk.service;

import com.cnzk.mapper.BillMapper;
import com.cnzk.mapper.CarMapper;
import com.cnzk.mapper.ChargeMapper;
import com.cnzk.mapper.ParkMapper;
import com.cnzk.pojo.Admin;
import com.cnzk.pojo.Charge;
import com.cnzk.pojo.LayuiData;
import com.cnzk.pojo.TbBill;
import com.cnzk.utils.MD5;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChargeServiceImpl implements ChargeService {


    @Resource
    private ChargeMapper chargeMapper;
    @Resource
    private CarMapper carMapper;
    @Resource
    private BillMapper billMapper;
    @Resource
    private ParkMapper parkMapper;


    //收费人员登陆
    @Override
    public String chargelogin(Map<String,Object> map, HttpSession session) {
        map.put("chargepass", MD5.machining(map.get("chargepass").toString()));//将管理员输入的密码转成MD5加密
        System.out.println(map);
        Admin tbAdmin2 = chargeMapper.chargelogin(map);
        if(tbAdmin2 != null){
            if (tbAdmin2.getRoleId()==3){
                if("启用".equals(tbAdmin2.getAdminState())){
                    session.setAttribute("tbAdmin",tbAdmin2);//将管理员信息放到session
                    return "success";
                }
                return "您已被禁止登陆！";
            }
            return "权限错误，请返回管理端登陆";
        }
        return "账号或密码错误";
    }
    @Override
    public LayuiData queryCharge(Charge charge, int start, int pageSize) {
        List<Charge> list = chargeMapper.queryChargeList(charge,start,pageSize);
        int count = chargeMapper.queryChargeCount(charge);
        LayuiData layuiData = new LayuiData();
        layuiData.setCount(count);
        layuiData.setData(list);
        layuiData.setCode(0);
        return layuiData;
    }

    @Override
    public Integer updateState(Charge charge) {
        return chargeMapper.updateState(charge);
    }

    @Override
    public Integer insertNewCharge(Charge charge) {
        return chargeMapper.insertNewCharge(charge);
    }

    @Override
    public Integer searchCharge(String adminName) {
        return chargeMapper.searchCharge(adminName);
    }

    @Override
    public Integer logOff(String adminName) {
        return chargeMapper.logOff(adminName);
    }

    @Override
    public Integer resetPass(Charge charge) {
        return chargeMapper.resetPass(charge);
    }

    @Override
    public Integer uploadHeadImg(Charge charge) {
        return chargeMapper.uploadHeadImg(charge);
    }

    @Override
    public Integer updateCharge(Charge charge) {
        return chargeMapper.updateCharge(charge);
    }
    //    实时出场现金支付
    @Override
    @Transactional
    public Integer cashPay(HashMap<String, Object> hashMap) {
        Integer row = 0;
        TbBill tbBill = new TbBill();
        tbBill.setBillNum(hashMap.get("billNum").toString());
        tbBill.setCarNum(hashMap.get("carnumber").toString());
        tbBill.setUserName(hashMap.get("username").toString());
        tbBill.setBillMoney(hashMap.get("money").toString());
        tbBill.setBillState(1);
        System.out.println("cashPay_tbBill="+tbBill);
        row += billMapper.insertCashBill(tbBill);
        row += carMapper.carexit(hashMap.get("carnumber").toString());
        row += parkMapper.carExit(tbBill);
        System.out.println(row);
        return row;
    }

    @Override
    public Integer freePay(HashMap<String, Object> hashMap) {
        Integer row = 0;
        TbBill tbBill = new TbBill();
        tbBill.setBillNum(hashMap.get("billNum").toString());
        tbBill.setCarNum(hashMap.get("carnumber").toString());
        tbBill.setUserName(hashMap.get("username").toString());
        tbBill.setBillMoney(hashMap.get("money").toString());
        tbBill.setBillState(1);
        System.out.println("cashPay_tbBill="+tbBill);
        row += carMapper.carexit(hashMap.get("carnumber").toString());
        row += parkMapper.carExit(tbBill);
        System.out.println(row);
        return row;
    }


}
