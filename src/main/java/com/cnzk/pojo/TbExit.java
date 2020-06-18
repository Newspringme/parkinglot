package com.cnzk.pojo;


import java.sql.Timestamp;

public class TbExit {

  private long exitId;
  private String exitTime;
  private String exitImg;
  private String carNum;


  public long getExitId() {
    return exitId;
  }

  public void setExitId(long exitId) {
    this.exitId = exitId;
  }


  public String getExitTime() {
    return exitTime;
  }

  public void setExitTime(String exitTime) {
    this.exitTime = exitTime;
  }


  public String getExitImg() {
    return exitImg;
  }

  public void setExitImg(String exitImg) {
    this.exitImg = exitImg;
  }


  public String getCarNum() {
    return carNum;
  }

  public void setCarNum(String carNum) {
    this.carNum = carNum;
  }

  public TbExit()
  {
  }

  public TbExit(long exitId, String exitTime, String exitImg, String carNum)
  {
    this.exitId = exitId;
    this.exitTime = exitTime;
    this.exitImg = exitImg;
    this.carNum = carNum;
  }

  @Override
  public String toString()
  {
    return "TbExit{" + "exitId=" + exitId + ", exitTime=" + exitTime + ", exitImg='" + exitImg + '\'' + ", carNum='" + carNum + '\'' + '}';
  }
}
