package com.cnzk.service;

import com.cnzk.mapper.AdminMapper;
import com.cnzk.mapper.RoleMapper;
import com.cnzk.pojo.Admin;
import com.cnzk.pojo.LayuiData;
import com.cnzk.pojo.TbRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleServeice{

    @Resource
    private RoleMapper roleMapper;
    @Override
    public LayuiData queryRole(int start, int pageSize) {
        List<TbRole> list=roleMapper.queryRole(start, pageSize);
        int count=roleMapper.queryRoleCount();
        LayuiData layuiData=new LayuiData();
        layuiData.setCode(0);
        layuiData.setCount(count);
        layuiData.setData(list);
        return layuiData;
    }

    @Override
    public int addRole(TbRole role) {
        return roleMapper.addRole(role);
    }

    @Override
    public int delecteRole(TbRole role) {
        return roleMapper.deleteRole(role);
    }

    @Override
    public int editRole(TbRole role) {
        return roleMapper.editRole(role);
    }

    //  可修改的角色的权限列表
    @Override
    public LayuiData queryRolesList(HashMap<String, Object> condition){

        Integer roleId = Integer.parseInt(condition.get("roleId").toString());
        TbRole tbRole = roleMapper.querySort(roleId);
        condition.put("roleSort",tbRole.getRoleSort());

        List<TbRole> tbRoleList = roleMapper.queryRolesList(condition);

        int count = roleMapper.queryCount(condition);
        LayuiData layuiData=new LayuiData();
        layuiData.setCode(0);
        layuiData.setCount(count);
        layuiData.setData(tbRoleList);
        return layuiData;
    }

    @Override
    public Integer updateMenuTree(String treeStr, Integer rolesid) {
        return null;
    }
}
