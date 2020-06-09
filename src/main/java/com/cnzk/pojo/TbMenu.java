package com.cnzk.pojo;


public class TbMenu {

  private long menuId;
  private String menuName;
  private String menuUrl;
  private long menuPid;
  private String menuIcon;
  private String meniTarget;

  //为权限修改mapper批量插入角色菜单关系表所加
  private Integer roleId;

  public long getMenuId() {
    return menuId;
  }

  public void setMenuId(long menuId) {
    this.menuId = menuId;
  }


  public String getMenuName() {
    return menuName;
  }

  public void setMenuName(String menuName) {
    this.menuName = menuName;
  }


  public String getMenuUrl() {
    return menuUrl;
  }

  public void setMenuUrl(String menuUrl) {
    this.menuUrl = menuUrl;
  }


  public long getMenuPid() {
    return menuPid;
  }

  public void setMenuPid(long menuPid) {
    this.menuPid = menuPid;
  }


  public String getMenuIcon() {
    return menuIcon;
  }

  public void setMenuIcon(String menuIcon) {
    this.menuIcon = menuIcon;
  }


  public String getMeniTarget() {
    return meniTarget;
  }

  public void setMeniTarget(String meniTarget) {
    this.meniTarget = meniTarget;
  }

  public Integer getRoleId() {
    return roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }

  @Override
  public String toString() {
    return "TbMenu{" +
            "menuId=" + menuId +
            ", menuName='" + menuName + '\'' +
            ", menuUrl='" + menuUrl + '\'' +
            ", menuPid=" + menuPid +
            ", menuIcon='" + menuIcon + '\'' +
            ", meniTarget='" + meniTarget + '\'' +
            ", roleId=" + roleId +
            '}';
  }
}
