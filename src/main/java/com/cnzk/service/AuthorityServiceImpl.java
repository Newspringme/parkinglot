package com.cnzk.service;

import com.cnzk.mapper.MenuMapper;
import com.cnzk.mapper.RoleMapper;
import com.cnzk.pojo.*;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Resource
    private MenuMapper menuMapper;
    @Resource
    private RoleMapper roleMapper;

    //  可修改的角色列表
    @Override
    public LayuiData queryRolesList(HashMap<String, Object> condition){

        Integer roleId = Integer.parseInt(condition.get("roleId").toString());
        TbRole tbRole = roleMapper.querySort(roleId);
        condition.put("roleSort",tbRole.getRoleSort());

        System.out.println("-----------------------"+condition);
        List<TbRole> tbRoleList = roleMapper.queryRolesList(condition);

        int count = roleMapper.queryCount(condition);
        LayuiData layuiData=new LayuiData();
        layuiData.setCode(0);
        layuiData.setCount(count);
        layuiData.setData(tbRoleList);
        return layuiData;
    }

}
