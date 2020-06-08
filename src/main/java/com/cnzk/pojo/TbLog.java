package com.cnzk.pojo;


public class TbLog {

  private long logId;
  private String operateName;
  private String operateThing;
  private java.sql.Timestamp operateTime;
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


  public java.sql.Timestamp getOperateTime() {
    return operateTime;
  }

  public void setOperateTime(java.sql.Timestamp operateTime) {
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
