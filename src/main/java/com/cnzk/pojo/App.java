package com.cnzk.pojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    public static Map getBill(String start, String end, TbRates rates) throws ParseException {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d2 = df.parse(start);
        Date d1 = df.parse(end);
        int bill=0;
        long l=d1.getTime()-d2.getTime();

        long day=l/(24*60*60*1000);

        long hour=(l/(60*60*1000)-day*24);

        long min=((l/(60*1000))-day*24*60-hour*60);
        if(day==0){
            if(min<30&&hour==0) {

            }else if(min>30&&hour==0){
                bill= (int) rates.getRatesUprice();
            }else if(hour>=1&&min==0){
                bill= (int) (rates.getRatesUprice()*hour);
            }else if(hour>=15){
                bill= (int) rates.getRatesMaxprice();
            }else {
                bill= (int) (rates.getRatesUprice()*(hour+1));
            }
        }else {
            if((hour>=1&&min==0)||(hour==0&&min==0)){
                bill= (int) (hour*rates.getRatesUprice()+day*rates.getRatesMaxprice());
            }else if(hour>=15){
                bill= (int) (rates.getRatesMaxprice()*(day+1));
            }else {
                bill= (int) ((hour+1)*rates.getRatesUprice()+day*rates.getRatesMaxprice());
            }
        }
        HashMap<String,String> map = new HashMap();
        map.put("bill",bill+"");
        map.put("stopTime","停车时长"+day+"天"+hour+"小时"+min+"分");
        return map;
    }
    public static String getoutTradeNo(){
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String exittime = sdf.format(new Date());//查车找人
        //        产生3个随机数
        String sjs="";
        for (int i = 0; i < 3; i++) {
            int max=9,min=0;
            int ran2 = (int) (Math.random()*(max-min)+min);
            sjs=ran2+sjs;
        }
//        利用正则提取时间数字
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(exittime);
        System.out.println( m.replaceAll("").trim());
//商户订单号，商户网站订单系统中唯一订单号，必填
        //生成随机Id
        String out_trade_no = m.replaceAll("").trim()+sjs;
        return out_trade_no;

    }
}
