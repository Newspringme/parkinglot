package com.cnzk.pojo;


public class TbRates {

  private Integer ratesId;
  private Integer ratesUprice;
  private Integer ratesMaxprice;


  public long getRatesId() {
    return ratesId;
  }

  public void setRatesId(Integer ratesId) {
    this.ratesId = ratesId;
  }


  public long getRatesUprice() {
    return ratesUprice;
  }

  public void setRatesUprice(Integer ratesUprice) {
    this.ratesUprice = ratesUprice;
  }


  public long getRatesMaxprice() {
    return ratesMaxprice;
  }

  public void setRatesMaxprice(Integer ratesMaxprice) {
    this.ratesMaxprice = ratesMaxprice;
  }

  @Override
  public String toString() {
    return "TbRates{" +
            "ratesId=" + ratesId +
            ", ratesUprice=" + ratesUprice +
            ", ratesMaxprice=" + ratesMaxprice +
            '}';
  }
}
