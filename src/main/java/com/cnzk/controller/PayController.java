package com.cnzk.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.cnzk.mapper.RatesMapper;
import com.cnzk.pojo.App;
import com.cnzk.pojo.LayuiData;
import com.cnzk.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Controller
public class PayController {
    private final String APP_ID = "2016102800774945";
    private final String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCrf7ME3iZ42rx0r89HkwMKSpaWuLLOvI73HkhOdUVd4uKy3WkjQ9b4d6Xl5uwj8oindBtDkW00qgUdPXzuKD/T4ko9AijFjO8aq7yYgF+6yxR4SQwvkQnrhSiuM0Xf9sArIPomVY2d7owKuTIHvakyhxIkc/AFxYdSlqBsHomTznp/Ff1JmjhVXNa+v8d2BiWJTQfNozumOsFpFGqCaRktM2l1FsjISNZsKjqv4FU0zmOnpdNUu4KZaBIFH5XUyZb6fIVY6g8w+ooDcF+ymyWGpyOo0sJvs7tiECorAeY4qMSv60bXNFUX+0ALwaD44R2DqWFTAZgVZi0m34OkHq+1AgMBAAECggEAJUuD9s8Itsd6rDV/ciU6SAaocOaBnAvNW1guiUFfcGdivVsA/jZGjYoXfcOWHssjhKbvNuZtRUKkEhPJE1GVb9KKKnJJw4w+gyJNxSxFpS18qhvYlo+IBLvUeO2ViHr0l2x4edWpEmtJ8RsNEjngjahp/Zto2xh1sTQodtq9OHMWwS1BU6F16Zivj25mJiX9iqBlDgAH76Hf2gmtCjxU71mlnH784iaXw4pPtIAaJEpH5MR0ymC/qwpG60NTtatvkYyZSuvl6ZHeIt1Rx7jP3SMOAU7Re73Mw3MEeaQSLzWmR4XFtHKX5+bIV5QrsSPOkxlImt3pLIrK22RNmc8GkQKBgQDtVD58nOSzDKNf2T5/YMgtwvO2M9h6j9ZARzeGSERhw7Rod7YBCLiH7yr5p8TYyNQKWQ4UGQspP6ue4MQ4MsPi5f3m/E330VetNwuRy3z3juci/jcTYpnDa5a9/j8vzd+1xkW0GRkIzI1KaMoP69+svZTymop7W/xZMqMmLXmtnwKBgQC4/aX0DJQC0nw6U0GFyrtHceQQ5KPG6dA3WCdYHz8+bFLe92ddmyU3YuDgDlybKpZUuck5LszWKDJkPdK58RJ4bV6YMPnEpfsZWrzO9jtJPFBdIVxVjXAJCmFtGNk0YeEHfbpQ0RHOuohS7UATbylAFFOX9aLLxxC+yoGcONe6KwKBgBWKG9G7e5Y0g29jjCSLaB+fHfdfZTpaa5uR2xw42tv0ox8IHmChpf3QWxole9wg/f2ib4CrdTPdL3pP8/8VvGmPbifUpk7jlQ2HfWlSCpl9QsUORXlg7Yuq7Bd6R8nS6YbZ4+GglYES74/dStl/EuHeovIRzJe9lX2S9vO0FOMJAoGAeSoBgViftT2CCn2vg98mTQRGud3Xe5h3PViq5GgK92rh2JI5DLFqbd2ApqAhfawmLLZHGwIRPg+z+b4YgJtqWqBf/dTOM0P3tEKfOftDF5O+m1EXdh2ejP1UlK1azplxV4O8/eQCAuJgGdi1SEVdwk/N9ND5YPkweZaPz+/XNGMCgYB4WFQn5Ly1ZoN/M0cFUDtQJEYkCx83PJPI/+RmmXiHmck1CbsvdWAAzTalnncs2PMOmv6YcanutJWIWWzISNmN+uXk9Ici6IBwu5uNWqjvry+T7iWwyrzB4ptOauz/HrtEBdntlz4+wzHwH+iiOTh09ztaSYgRoCY6xA8jzQw9rg==";
    private final String CHARSET = "UTF-8";
    private final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjtfS2TX8VFash0JpSZU5/7K2D4YCK+eG54ND5FkGUEMaTjbwQgU0C4lzy/6XWmJH2k+5IEpZechic1vX+IcHpkXO+5AqS18f18oyExS//CaPmEjK0J8vEKg9kauYHawiMlJx8LsLG8QAueaCHBAqKRAOELHJjG3WabQVgd902mtATBH1MphbFI/lsR4V43PeELcL8aHZAYJzhSFF+BUZ/UlLcQj8dgN5VlEcjffMouO2XyuntcrBixOpPzBzF3GwIAMN3jaJQp/hZHhlRvBVCw/0LiGj8GB68MGKbuA976bX+NkWIfXN/Gb7WFKbJVVoN6X5rIPgl2kAqIhJF9pjTQIDAQAB";
    //这是沙箱接口路径,正式路径为https://openapi.alipay.com/gateway.do
    private final String GATEWAY_URL ="https://openapi.alipaydev.com/gateway.do";
    private final String FORMAT = "JSON";
    //签名方式
    private final String SIGN_TYPE = "RSA2";
    //支付宝异步通知路径,付款完毕后会异步调用本项目的方法,必须为公网地址
    private final String NOTIFY_URL = "http://www.badiu.com";
//    //支付宝同步通知路径,也就是当付款完毕后跳转本项目的页面,可以不是公网地址
    private final String RETURN_URL = "http://localhost:8080/parkinglot/url/departure";

    @Autowired
    private AdminService adminService;

    @RequestMapping("alipay")
    public void alipay(HttpServletResponse httpResponse,String enter,String exit) throws IOException, ParseException {
        System.out.println(enter);
        System.out.println(exit);
        Random r=new Random();
        //实例化客户端,填入所需参数
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //在公共参数中设置回跳和通知地址
        request.setReturnUrl(RETURN_URL);
        request.setNotifyUrl(NOTIFY_URL);


        //商户订单号，商户网站订单系统中唯一订单号，必填
        //生成随机Id
        String out_trade_no = UUID.randomUUID().toString();
        //付款金额，必填
//        String total_amount =Integer.toString(100000);
        Map<String ,String> map = App.getBill(enter,exit,adminService.queryPrice());
        String total_amount = map.get("bill");
        //订单名称，必填
        String subject ="菜鸟停车场"+map.get("stopTime");
        //商品描述，可空
        String body = "菜鸟停车场自助缴费"+map.get("stopTime");
        request.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String form = "";
        try {
            form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

//    @ResponseBody
//    @RequestMapping("exit")
//    public Object queryRole(String page,String limit){
//        int startPage=Integer.parseInt(page);//获取页码;
//        int pageSize=Integer.parseInt(limit);//每页数量
//        int start = (startPage-1)*pageSize;//计算出起始查询位置
////        LayuiData layuiData=roleService.queryRole(start,pageSize);
////        System.out.println("layuiData = " + JSON.toJSONString(layuiData));
//        return layuiData;
//    }



}
