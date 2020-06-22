package com.cnzk.test;

import com.cnzk.controller.PayController;
import com.cnzk.pojo.TbRates;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String[] args) throws ParseException {
        PayController p = new PayController();
        p.carbill("car","苏DS0000");
    }

    public static int getBill(String start, String end, TbRates rates) throws ParseException {
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

        return bill;
    }
}
