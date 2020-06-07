package com.cnzk.pojo;


/**
 * @author LQ
 */
public class TbMenu {

  private Long menuId;
  private String menuName;
  private String menuUrl;
  private Long menuPid;

  //为权限修改mapper批量插入角色菜单关系表所加
  private Long rolesid;

  public Long getMenuId() {
    return menuId;
  }

  public void setMenuId(Long menuId) {
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


  public Long getMenuPid() {
    return menuPid;
  }

  public void setMenuPid(Long menuPid) {
    this.menuPid = menuPid;
  }

  public Long getRolesid() {
    return rolesid;
  }

  public void setRolesid(Long rolesid) {
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
