package com.cnzk.pojo;


/**
 * @author LQ
 */
public class TbMenu {

  private long menuId;
  private String menuName;
  private String menuUrl;
  private long menuPid;


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

}
