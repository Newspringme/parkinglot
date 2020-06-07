package com.cnzk.pojo;


import java.util.List;

public class TbRole {

  private Long roleId;
  private String roleName;
  private Integer roleSort;
  private List<TbMenu> menutblList;

  public TbRole() {
  }

  public TbRole(Long roleId, String roleName) {
    this.roleId = roleId;
    this.roleName = roleName;
  }

  public TbRole(Long roleId, String roleName, Integer roleSort, List<TbMenu> menutblList) {
    this.roleId = roleId;
    this.roleName = roleName;
    this.roleSort = roleSort;
    this.menutblList = menutblList;
  }

  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }


  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public Integer getRoleSort() {
    return roleSort;
  }

  public void setRoleSort(Integer roleSort) {
    this.roleSort = roleSort;
  }

  public List<TbMenu> getMenutblList() {
    return menutblList;
  }

  public void setMenutblList(List<TbMenu> menutblList) {
    this.menutblList = menutblList;
  }
}
