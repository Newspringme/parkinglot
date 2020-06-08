package com.cnzk.pojo;


import java.text.SimpleDateFormat;

public class TbLog {

  private long logId;
  private String operateName;
  private String operateThing;
  private String operateTime;
  private String operateType;
  private String operateIp;


  public long getLogId() {
    return logId;
  }

  public void setLogId(long logId) {
    this.logId = logId;
  }


  public String getOperateName() {
    return operateName;
  }

  public void setOperateName(String operateName) {
    this.operateName = operateName;
  }


  public String getOperateThing() {
    return operateThing;
  }

  public void setOperateThing(String operateThing) {
    this.operateThing = operateThing;
  }


  public String getOperateTime() {
    return operateTime;
  }

  public void setOperateTime(String operateTime) {
    this.operateTime = operateTime;
  }


  public String getOperateType() {
    return operateType;
  }

  public void setOperateType(String operateType) {
    this.operateType = operateType;
  }


  public String getOperateIp() {
    return operateIp;
  }

  public void setOperateIp(String operateIp) {
    this.operateIp = operateIp;
  }

}
