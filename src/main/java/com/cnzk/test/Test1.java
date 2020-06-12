package com.cnzk.test;

import com.cnzk.pojo.TbRates;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Test1
{
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date d2 = df.parse("2004-03-26 11:31:40");

        Date d1 = df.parse("2004-03-27 11:31:40");

        long l=d1.getTime()-d2.getTime();

        long day=l/(24*60*60*1000);

        long hour=(l/(60*60*1000)-day*24);

        long min=((l/(60*1000))-day*24*60-hour*60);
        if(day==0){
            if(min<30&&hour==0) {
                System.out.println("free");
            }else if(min>30&&hour==0){
                System.out.print(2);
                System.out.println("$");
            }else if(hour>=1&&min==0){
                System.out.print(hour*2);
                System.out.println("$");
            }else if(hour>=15){
                System.out.print(30);
                System.out.println("$");
            }else {
                System.out.print(hour*2+2);
                System.out.println("$");
            }
        }else {
            if((hour>=1&&min==0)||(hour==0&&min==0)){
                System.out.print(hour*2+day*30);
                System.out.println("$");
            }else if(hour>=15){
                System.out.print(30+day*30);
                System.out.println("$");
            }else {
                System.out.print(hour*2+2+day*30);
                System.out.println("$");
            }
        }



        System.out.println(""+day+"天"+hour+"小时"+min+"分");
        System.out.println(hour);
        System.out.println(min);
    }

    public static Map<String,Object> getBill(String start, String end, TbRates rates) throws ParseException {
        HashMap map = new HashMap();

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

        map.put("bill", bill);
        map.put("time", ""+day+"天"+hour+"小时"+min+"分");
        return map;
    }
}
