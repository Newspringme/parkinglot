package com.cnzk.service;

import com.alibaba.fastjson.JSON;
import com.cnzk.mapper.AdminMapper;
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
    @Transactional
    public Integer updateMenuTree(String treeStr, Integer roleId) {
        Integer row = 0;
        List<TreeData> treeData = JSON.parseArray(treeStr,TreeData.class);
        List<TbMenu> menutblList = new ArrayList<TbMenu>();
        menutblList = digui(treeData,roleId,menutblList);
        System.out.println(menutblList.toString());
        row += roleMapper.deleRolesMenu(roleId);
        row += roleMapper.addRolesMenu(menutblList);
        return row;
    }



    public static void main(String[] args) {
    String treeStr = "[{'id':100,'pid':0,'title':'管理员','spread':true,'checked':false,'children':[{'id':34,'pid':100,'title':'系统管理','icon':'fa fa-user','href':'','target':'','spread':true,'checked':false,'children':[{'id':18,'pid':34,'title':'角色管理','icon':'fa fa-street-view','href':'/parkinglot/pages/role-table.jsp','target':'_self','spread':true,'checked':false}]}]},{'id':101,'pid':0,'title':'收费员','target':'','spread':true,'checked':false,'children':[{'id':6,'pid':101,'title':'个人信息','icon':'fa fa-usd','href':'','spread':true,'checked':false,'children':[{'id':37,'pid':6,'title':'人脸录入','icon':'fa fa-user','href':'/parkinglot/pages/addChargeFace.jsp','target':'_blank','spread':true,'checked':true}]},{'id':35,'pid':101,'title':'月缴办理','icon':'fa fa-user','href':'/parkinglot/pages/combo_handle.jsp','target':'','spread':true,'checked':false,'children':[{'id':22,'pid':35,'title':'月卡办理','icon':'fa fa-flag-o','target':'_self','spread':true,'checked':true},{'id':23,'pid':35,'title':'月卡续费','icon':'fa fa-flag-checkered','target':'_self','spread':true,'checked':true}]},{'id':39,'pid':101,'title':'停车场信息','icon':'fa fa-tags','spread':true,'checked':false,'children':[{'id':24,'pid':39,'title':'查看停车场','icon':'fa fa-arrows','href':'/parkinglot/pages/parkinglot_map.jsp','target':'_self','spread':true,'checked':true},{'id':25,'pid':39,'title':'历史出场记录','icon':'fa fa-table','href':'/parkinglot/pages/exit_history.jsp','target':'_self','spread':true,'checked':true},{'id':32,'pid':39,'title':'实时出库','icon':'fa fa-car','href':'/parkinglot/pages/realtime_exit.jsp','target':'_self','spread':true,'checked':true},{'id':38,'pid':39,'title':'场内车辆信息','icon':'fa fa-car','target':'_self','spread':true,'checked':true}]}]}]";
        Integer roleId = 3;
        List<TreeData> treeData = JSON.parseArray(treeStr,TreeData.class);
        List<TbMenu> menutblList = new ArrayList<TbMenu>();
        menutblList = digui(treeData,roleId,menutblList);
        System.out.println(menutblList.toString());
    }


    public static List<TbMenu> digui(List<TreeData> treeData,Integer roleId,List<TbMenu> menutblList){
        System.out.println(treeData.size());
        for (int i=0;i<treeData.size();i++){
            TbMenu tbMenu = new TbMenu();
            tbMenu.setMenuId(treeData.get(i).getId());
            tbMenu.setRoleId(roleId);
            tbMenu.setMenuName(treeData.get(i).getTitle());
            System.out.println(tbMenu);
            if (treeData.get(i).getChildren()!=null){
                digui(treeData.get(i).getChildren(),roleId,menutblList);
                tbMenu.setIsthreeMenu(1);
            }else{
                tbMenu.setIsthreeMenu(0);
            }
            menutblList.add(tbMenu);
        }
        return menutblList;
    }
}