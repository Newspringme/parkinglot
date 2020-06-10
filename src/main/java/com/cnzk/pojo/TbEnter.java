package com.cnzk.pojo;


import java.sql.Timestamp;

public class TbEnter {

  private long enterId;
  private java.sql.Timestamp enterTime;
  private String enterImg;
  private String carNum;


  public long getEnterId() {
    return enterId;
  }

  public void setEnterId(long enterId) {
    this.enterId = enterId;
  }


  public java.sql.Timestamp getEnterTime() {
    return enterTime;
  }

  public void setEnterTime(java.sql.Timestamp enterTime) {
    this.enterTime = enterTime;
  }


  public String getEnterImg() {
    return enterImg;
  }

  public void setEnterImg(String enterImg) {
    this.enterImg = enterImg;
  }


  public String getCarNum() {
    return carNum;
  }

  public void setCarNum(String carNum) {
    this.carNum = carNum;
  }

  public TbEnter()
  {
  }

  public TbEnter(long enterId, Timestamp enterTime, String enterImg, String carNum)
  {
    this.enterId = enterId;
    this.enterTime = enterTime;
    this.enterImg = enterImg;
    this.carNum = carNum;
  }

  @Override
  public String toString()
  {
    return "TbEnter{" + "enterId=" + enterId + ", enterTime=" + enterTime + ", enterImg='" + enterImg + '\'' + ", carNum='" + carNum + '\'' + '}';
  }
}
