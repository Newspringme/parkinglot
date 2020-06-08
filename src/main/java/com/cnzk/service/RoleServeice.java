package com.cnzk.service;

import com.cnzk.pojo.LayuiData;
import com.cnzk.pojo.TbRole;

import java.util.HashMap;

public interface RoleServeice {
    LayuiData queryRole( int start, int pageSize);
    int addRole(TbRole role);
    int delecteRole(TbRole role);
    int editRole(TbRole role);

    //  权限：可修改的角色列表
    LayuiData queryRolesList(HashMap<String, Object> condition);

    //  修改权限
    Integer updateMenuTree(String treeStr, Integer rolesid);
}
