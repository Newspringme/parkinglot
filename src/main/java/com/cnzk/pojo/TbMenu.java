package com.cnzk.pojo;


/**
 * @author LQ
 */
public class TbMenu {

  private Integer menuId;
  private String menuName;
  private String menuUrl;
  private Integer menuPid;

  //为权限修改mapper批量插入角色菜单关系表所加
  private Integer rolesid;

  public Integer getMenuId() {
    return menuId;
  }

  public void setMenuId(Integer menuId) {
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


  public Integer getMenuPid() {
    return menuPid;
  }

  public void setMenuPid(Integer menuPid) {
    this.menuPid = menuPid;
  }

  public Integer getRolesid() {
    return rolesid;
  }

  public void setRolesid(Integer rolesid) {
    this.rolesid = rolesid;
  }

  @Override
  public String toString() {
    return "TbMenu{" +
            "menuId=" + menuId +
            ", menuName='" + menuName + '\'' +
            ", menuUrl='" + menuUrl + '\'' +
            ", menuPid=" + menuPid +
            ", rolesid=" + rolesid +
            '}';
  }
}
