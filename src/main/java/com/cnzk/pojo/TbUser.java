package com.cnzk.pojo;


import java.sql.Timestamp;

public class TbUser {

  private long userId;
  private String userName;
  private String userPass;
  private String userCard;
  private String userSex;
  private long userAge;
  private String userTel;
  private String headImg;
  private long carId;
  private java.sql.Timestamp regTime;


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getUserPass() {
    return userPass;
  }

  public void setUserPass(String userPass) {
    this.userPass = userPass;
  }


  public String getUserCard() {
    return userCard;
  }

  public void setUserCard(String userCard) {
    this.userCard = userCard;
  }


  public String getUserSex() {
    return userSex;
  }

  public void setUserSex(String userSex) {
    this.userSex = userSex;
  }


  public long getUserAge() {
    return userAge;
  }

  public void setUserAge(long userAge) {
    this.userAge = userAge;
  }


  public String getUserTel() {
    return userTel;
  }

  public void setUserTel(String userTel) {
    this.userTel = userTel;
  }


  public String getHeadImg() {
    return headImg;
  }

  public void setHeadImg(String headImg) {
    this.headImg = headImg;
  }


  public long getCarId() {
    return carId;
  }

  public void setCarId(long carId) {
    this.carId = carId;
  }


  public java.sql.Timestamp getRegTime() {
    return regTime;
  }

  public void setRegTime(java.sql.Timestamp regTime) {
    this.regTime = regTime;
  }

  public TbUser()
  {
  }

  public TbUser(long userId, String userName, String userPass, String userCard, String userSex, long userAge, String userTel, String headImg, long carId, Timestamp regTime)
  {
    this.userId = userId;
    this.userName = userName;
    this.userPass = userPass;
    this.userCard = userCard;
    this.userSex = userSex;
    this.userAge = userAge;
    this.userTel = userTel;
    this.headImg = headImg;
    this.carId = carId;
    this.regTime = regTime;
  }

  @Override
  public String toString()
  {
    return "TbUser{" + "userId=" + userId + ", userName='" + userName + '\'' + ", userPass='" + userPass + '\'' + ", userCard='" + userCard + '\'' + ", userSex='" + userSex + '\'' + ", userAge=" + userAge + ", userTel='" + userTel + '\'' + ", headImg='" + headImg + '\'' + ", carId=" + carId + ", regTime=" + regTime + '}';
  }
}
