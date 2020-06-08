package com.cnzk.pojo;


public class Charge {

  private int adminId;
  private String adminName;
  private String adminPass;
  private String adminSex;
  private String headImg;
  private int roleId;
  private String adminTel;
  private String regTime;
  private String adminFace;
  private String adminState;
  private String startTime;
  private String endTime;

  private TbRole tbRole;

    public TbRole getTbRole() {
        return tbRole;
    }

    public void setTbRole(TbRole tbRole) {
        this.tbRole = tbRole;
    }

    public String getAdminName() {
    return adminName;
  }

  public void setAdminName(String adminName) {
    this.adminName = adminName;
  }


  public String getAdminPass() {
    return adminPass;
  }

  public void setAdminPass(String adminPass) {
    this.adminPass = adminPass;
  }


  public String getAdminSex() {
    return adminSex;
  }

  public void setAdminSex(String adminSex) {
    this.adminSex = adminSex;
  }


  public String getHeadImg() {
    return headImg;
  }

  public void setHeadImg(String headImg) {
    this.headImg = headImg;
  }


  public int getRoleId() {
    return roleId;
  }

  public void setRoleId(int roleId) {
    this.roleId = roleId;
  }


  public String getAdminTel() {
    return adminTel;
  }

  public void setAdminTel(String adminTel) {
    this.adminTel = adminTel;
  }


  public String getRegTime() {
    return regTime;
  }

  public void setRegTime(String regTime) {
    this.regTime = regTime;
  }


  public String getAdminFace() {
    return adminFace;
  }

  public void setAdminFace(String adminFace) {
    this.adminFace = adminFace;
  }


  public String getAdminState() {
    return adminState;
  }

  public void setAdminState(String adminState) {
    this.adminState = adminState;
  }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


}
