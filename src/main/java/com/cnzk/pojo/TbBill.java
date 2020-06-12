package com.cnzk.pojo;


public class TbBill {

  private long billId;
  private String billTime;
  private String billNum;
  private String billMoney;
  private long comboId;

//  操作事项
  private String comboName;

//  操作人
  private String userName;


  public long getBillId() {
    return billId;
  }

  public void setBillId(long billId) {
    this.billId = billId;
  }


  public String getBillTime() {
    return billTime;
  }

  public void setBillTime(String billTime) {
    this.billTime = billTime;
  }


  public String getBillNum() {
    return billNum;
  }

  public void setBillNum(String billNum) {
    this.billNum = billNum;
  }


  public String getBillMoney() {
    return billMoney;
  }

  public void setBillMoney(String billMoney) {
    this.billMoney = billMoney;
  }


  public long getComboId() {
    return comboId;
  }

  public void setComboId(long comboId) {
    this.comboId = comboId;
  }

  public String getComboName() {
    return comboName;
  }

  public void setComboName(String comboName) {
    this.comboName = comboName;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  @Override
  public String toString() {
    return "TbBill{" +
            "billId=" + billId +
            ", billTime='" + billTime + '\'' +
            ", billNum='" + billNum + '\'' +
            ", billMoney='" + billMoney + '\'' +
            ", comboId=" + comboId +
            ", comboName='" + comboName + '\'' +
            ", userName='" + userName + '\'' +
            '}';
  }
}