package com.cnzk.pojo;

public class TbCombo {

  private long comboId;
  private String comboName;
  private String comboValue;


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


  public String getComboValue() {
    return comboValue;
  }

  public void setComboValue(String comboValue) {
    this.comboValue = comboValue;
  }

  @Override
  public String toString() {
    return "TbCombo{" +
            "comboId=" + comboId +
            ", comboName='" + comboName + '\'' +
            ", comboValue='" + comboValue + '\'' +
            '}';
  }
}
