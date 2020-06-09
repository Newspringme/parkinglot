package com.cnzk.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
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
}
