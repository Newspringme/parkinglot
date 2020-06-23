package com.cnzk.service;

import com.cnzk.mapper.FeedbackMapper;
import com.cnzk.mapper.BillMapper;
import com.cnzk.mapper.SlideshowMapper;
import com.cnzk.mapper.UserMapper;
import com.cnzk.pojo.TbFeedback;
import com.cnzk.pojo.LayuiData;
import com.cnzk.pojo.TbBill;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
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
    public LayuiData weiXinQueryBill(String carNum){
        List<TbBill> list=billMapper.weiXinQueryBill(carNum);
        for (TbBill tbBill : list) {
            if (null==tbBill.getComboName()) {
                tbBill.setComboName("临时停车");
            }else{
                tbBill.setComboName("购买"+tbBill.getComboName());
            }
        }
        LayuiData layuiData = new LayuiData();
        layuiData.setData(list);
        return layuiData;
    }

    //    根据订单编号查账单信息
    @Override
    public TbBill queryBilldetails(String carNum, String billNum) {
        TbBill tbBill=billMapper.queryBilldetails(carNum,billNum);
        if (null==tbBill.getComboName()) {
            tbBill.setComboName("临时停车");
        }else{
            tbBill.setComboName("购买"+tbBill.getComboName());
        }
        return tbBill;
    }
}
