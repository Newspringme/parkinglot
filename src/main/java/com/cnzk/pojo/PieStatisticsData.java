package com.cnzk.pojo;

public class PieStatisticsData {
    private String name;
    private Integer value;
    private Boolean selected = false;

    public PieStatisticsData() {
    }

    public PieStatisticsData(String name, Integer value, Boolean selected) {
        this.name = name;
        this.value = value;
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "PieStatisticsData{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", selected=" + selected +
                '}';
    }
}
