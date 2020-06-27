package com.cnzk.service;

import com.cnzk.mapper.*;
import com.cnzk.pojo.TbCar;
import com.cnzk.pojo.TbFeedback;
import com.cnzk.pojo.LayuiData;
import com.cnzk.pojo.TbBill;
import com.cnzk.pojo.TbUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class WeiXinServiceImpl implements WeiXinService{
    @Resource
    private SlideshowMapper slideshowMapper;
    @Resource
    private FeedbackMapper feedbackMapper;
    @Resource
    private BillMapper billMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private ParkMapper parkMapper;
    @Resource
    private CarMapper carMapper;


    @Override
    public List<String> queryImgUrl() {
        Date date = new Date();
        //使用UUID+后缀名保存文件名，防止中文乱码问题
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String time = simpleDateFormat.format(date);
        return slideshowMapper.queryImgUrl(time);
    }

    @Override
    public Integer feedback(TbFeedback feedback) {
        return feedbackMapper.feedback(feedback);
    }

//    小程序订单查询
    @Override
    public LayuiData weiXinQueryBill(List<TbCar> tbCarList){
        List<TbBill> tbBillList = new ArrayList<>();
        for(TbCar tbCar: tbCarList){
            List<TbBill> list = new ArrayList<>();
            list = billMapper.weiXinQueryBill(tbCar.getCarNum());
            tbBillList.addAll(list);
        }
        for (TbBill tbBill : tbBillList) {
            if (null==tbBill.getComboName()) {
                tbBill.setComboName("临时停车");
            }else{
                tbBill.setComboName("购买"+tbBill.getComboName());
            }
        }
        LayuiData layuiData = new LayuiData();
        layuiData.setData(tbBillList);
        return layuiData;
    }

    //    根据订单编号查账单信息
    @Override
    public TbBill queryBilldetails(String billNum) {
        TbBill tbBill=billMapper.queryBilldetails(billNum);
        if (null==tbBill.getComboName()) {
            tbBill.setComboName("临时停车");
        }else{
            tbBill.setComboName("购买"+tbBill.getComboName());
        }
        return tbBill;
    }

    @Override
    public Integer UpdateUser(TbUser user) {
        return userMapper.updataUser(user);
    }
    //    查看空车位
    @Override
    public Integer queryNullPark() {
        return parkMapper.queryNullPark();
    }
    //    根据手机号查车牌
    @Override
    public List<TbCar> queryCarNum(String userTel) {
        return carMapper.queryCarNum(userTel);
    }

    @Override
    public TbUser queryUser(String userTel) {
        return userMapper.queryUser(userTel);
    }
}
