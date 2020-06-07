package com.cnzk.pojo;


import java.util.List;

public class TbRole {

  private Integer roleId;
  private String roleName;
  private Integer roleSort;
  private List<TbMenu> menutblList;

  public TbRole() {
  }

  public TbRole(Integer roleId, String roleName) {
    this.roleId = roleId;
    this.roleName = roleName;
  }

  public TbRole(Integer roleId, String roleName, Integer roleSort, List<TbMenu> menutblList) {
    this.roleId = roleId;
    this.roleName = roleName;
    this.roleSort = roleSort;
    this.menutblList = menutblList;
  }

  public Integer getRoleId() {
    return roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }


  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

}
