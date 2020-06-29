package com.cnzk.service;

import com.alibaba.fastjson.JSONObject;
import com.cnzk.controller.HttpClientUtil;
import com.cnzk.controller.UserConstantInterface;
import com.cnzk.mapper.*;
import com.cnzk.pojo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        if("1".equals(tbBill.getBillState())){
            tbBill.setBillType("支付成功");
        }else{
            tbBill.setBillType("支付失败");
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

	@Override
	public ResultData weChatLogin(String code, String userHead, String userName, String userGender) {
		// 配置请求参数
		Map<String, String> param = new HashMap<>();
		param.put("appid", UserConstantInterface.WX_LOGIN_APPID);
		param.put("secret", UserConstantInterface.WX_LOGIN_SECRET);
		param.put("js_code", code);
		param.put("grant_type", UserConstantInterface.WX_LOGIN_GRANT_TYPE);
		// 发送请求
		String wxResult = HttpClientUtil.doGet(UserConstantInterface.WX_LOGIN_URL, param);
		JSONObject jsonObject = JSONObject.parseObject(wxResult);
		// 获取参数返回的
		String session_key = jsonObject.get("session_key").toString();
		String open_id = jsonObject.get("openid").toString();

		System.out.println("-----------openId:"+open_id);

		ResultData resultData = new ResultData();
		TbUser user = userMapper.queryOpenIdUser(open_id);
		if(user!=null){
			System.out.println("用户 登陆");
			user.setRegTime(new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(new Date()));
			user.setUserCard(open_id);
			userMapper.uploadLoginTime(user);
//			List<User> list = new ArrayList<>();
//			list.add(user);
			resultData.setData(user);

		} else {
			TbUser addUser = new TbUser();
			addUser.setUserCard(open_id);
			addUser.setUserName(userName);
			addUser.setUserSex(userGender);
			addUser.setHeadImg(userHead);
			addUser.setRegTime(new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(new Date()));
			int addRes = userMapper.insertUserInfo(addUser);
			if(addRes>=1){
				resultData.setCode(0);
				resultData.setMsg("添加用户成功");
				resultData.setData(addUser);
			} else {
				resultData.setCode(0);
				resultData.setMsg("添加用户失败");
			}
		}
		resultData.setMsg(wxResult);
		System.out.println("-------------微信登录："+wxResult);
		return resultData;
	}

    //    根据用户标识查车牌
    @Override
    public List<TbCar> queryCarNum(String userCard) {
        return carMapper.queryCarNum(userCard);
    }

    @Override
    public TbUser queryUser(String userTel) {
        return userMapper.queryUser(userTel);
    }

}
