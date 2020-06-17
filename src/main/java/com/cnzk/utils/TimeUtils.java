package com.cnzk.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TimeUtils {

    public static List weeklist;
    public static List moulist;
    //半年的时间段集合
    public static List getYearSection()  {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");//设置日期格式
        String date = df.format(new Date());//获取当前时间
        System.out.println(date);
        Integer mon = Integer.parseInt(date.split("-")[1]);
        Integer year = Integer.parseInt(date.split("-")[0]);
        System.out.println(mon);//当前月
        List Xlist = new ArrayList();
        List dateSection = new ArrayList();
//        Xlist.add((mon+1)+"月");
        df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String date1 = df.format(new Date());//获取当前时间
        dateSection.add(date1);
        for(int i=0;i<mon;i++) {
            if(i<6) {//半年限制
                Integer monstr = mon-i;
                if(monstr<10) {
                    dateSection.add(year+"-0"+monstr+"-01");
                }else {
                    dateSection.add(year+"-"+monstr+"-01");
                }
                Xlist.add(monstr+"月");
            }
        }
        if(dateSection.size()<7) {//月份不足时，从上一年开始
            Integer count = 0;
            Integer surplus = 7-dateSection.size();
            year = year-1;
            Integer mon1 = 0;
            while(true) {
                if(surplus.equals(count)) {
                    break;
                }else {
                    mon1 = 12-count;
                    if(mon1<10) {
                        dateSection.add(year+"-0"+mon1+"-01");

                    }else {
                        dateSection.add(year+"-"+mon1+"-01");
                    }
                    //再加一个到当天的
                    count++;
                    Xlist.add(mon1+"月");
                }
            }
        }
        System.out.println(dateSection.toString());
        System.out.println(Xlist.toString());
        moulist = new ArrayList();
        List dateSection1 = new ArrayList();
        for(int x=Xlist.size()-1;x>=0;x--) {
            moulist.add(Xlist.get(x));
        }
        for(int y=dateSection.size()-1;y>=0;y--) {
            dateSection1.add(dateSection.get(y));
        }
        System.out.println(dateSection1.toString());
        System.out.println(moulist);
        return dateSection1;
    }

    //本月的时间段集合
    public static List getDateSection(String sysdate) throws ParseException {
        String date = sysdate;
        weeklist = new ArrayList();//x轴的
        List wsectionlist = new ArrayList();//与之对应周的范围
//		SimpleDateFormat 使得可以选择任何用户定义的日期-时间格式的模式。
//		建议通过 DateFormat 中的 getTimeInstance、getDateInstance 或 getDateTimeInstance 来创建日期-时间格式器。
//		每一个这样的类方法都能够返回一个以默认格式模式初始化的日期/时间格式器。
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        Date date1 = dateFormat.parse(date);//从给定字符串的开始解析文本，以生成一个日期。

//      Calendar一个抽象类为操作日历字段（例如获得下星期的日期）提供了一些方法,	获得并设置日历字段值
        Calendar calendar = new GregorianCalendar();//提供了世界上大多数国家/地区使用的标准日历系统。
        calendar.setTime(date1);
//        DAY_OF_MONTH指示一个月中的某天。它与 DATE 是同义词。一个月中第一天的值为 1。
//        getActualMaximum(int field) 给定此 Calendar 的时间值，返回指定日历字段可能拥有的最大值。
        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);//getActualMaximum本月的最大值
        System.out.println("days:" + days);
        int count = 0;//用来计算第几周
        String datestr = "";
        for (int i = 1; i <= days; i++) {
            DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
            Date date2 = null;
            date2 = dateFormat1.parse(date + "-" + i);
//           calendar.clear() 将此 Calendar 的所日历字段值和时间值（从历元至现在的毫秒偏移量）设置成未定义
            calendar.clear();//先清除旧日期再重新设置
            calendar.setTime(date2);
//            DAY_OF_WEEK指示一个星期中的某天
//            SUNDAY、MONDAY、TUESDAY、WEDNESDAY、THURSDAY、FRIDAY 和 SATURDAY。
            int k = new Integer(calendar.get(Calendar.DAY_OF_WEEK));
            String weekstr = "";
            String wliststr = "";
            if (k == 1) {// 若当天是周日，日历从周日开始，周日是1
                count++;
                weekstr = "第" + count + "周";
                weeklist.add(weekstr);
                if (i - 6 <= 1) {
                    wliststr = date + "-" + 1;

                    wsectionlist.add(wliststr);
                } else {
                    wliststr = date + "-" + (i - 6);
                    wsectionlist.add(wliststr);
                }
                wliststr = date + "-" + i;
                wsectionlist.add(wliststr);
            }
            if (k != 1 && i == days) {// 若是本月最后一天，且不是周日
                count++;
                weekstr = "第" + count + "周";
                weeklist.add(weekstr);
                wliststr = date + "-" + (i - k + 2);
                wsectionlist.add(wliststr);
                wliststr = date + "-" + i;
                wsectionlist.add(wliststr);
            }
        }
        List mounthlist = new ArrayList();
        for (int i = 0; i < wsectionlist.size(); i++) {
            String a = wsectionlist.get(i).toString();
            String b = a.split("-")[0];
            String c = a.split("-")[1];
            Integer d = Integer.valueOf(a.split("-")[2]);
            String e;
            if(d < 10) {
                e = "0"+d.toString();
            }else {
                e = d.toString();
            }
            String mounth = b+"-"+c+"-"+e;
            System.out.println(mounth);
            mounthlist.add(mounth);
        }

        System.out.println(mounthlist);
        System.out.println(weeklist);
        return mounthlist;
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");//设置日期格式
        String date = df.format(new Date());//获取当前时间
        System.out.println(getYearSection());
    }
}
