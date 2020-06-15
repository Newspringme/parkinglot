package com.cnzk.pojo;

import java.util.HashMap;

//统计报表用到的data
public class StatisticsData {

    private HashMap<String,Object> statisticsMap;
    private String ALL_DAY_DATE;




    public StatisticsData(String ALL_DAY_DATE) {
        this.ALL_DAY_DATE = ALL_DAY_DATE;
    }

    public HashMap<String, Object> getStatisticsMap() {
        return statisticsMap;
    }

    public void setStatisticsMap(HashMap<String, Object> statisticsMap) {
        this.statisticsMap = statisticsMap;
    }

    public StatisticsData(HashMap<String, Object> statisticsMap) {
        this.statisticsMap = statisticsMap;
    }

    public String getALL_DAY_DATE() {
        return ALL_DAY_DATE;
    }

    public void setALL_DAY_DATE(String ALL_DAY_DATE) {
        this.ALL_DAY_DATE = ALL_DAY_DATE;
    }


    @Override
    public String toString() {
        return "StatisticsData{" +
                "statisticsMap=" + statisticsMap +
                ", ALL_DAY_DATE='" + ALL_DAY_DATE + '\'' +
                '}';
    }

    public StatisticsData() {
    }
}
