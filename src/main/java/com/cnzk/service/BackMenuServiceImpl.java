package com.cnzk.service;

import com.cnzk.mapper.MenuMapper;
import com.cnzk.pojo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class BackMenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Override
    public LinkedHashMap<String, List<TbMenu>> findMenus(Integer rolesid){
//        通过角色id找二级菜单
        List<TbMenu> menutblList1 =menuMapper.findMenu(rolesid);
        System.out.println("menuList1="+menutblList1);
//        通过角色id找一级菜单
        List<TbMenu> menutblsonList =menuMapper.findMenuSon(rolesid);
        System.out.println("menusonList="+menutblsonList);
//        将二级菜单放到对应的一级菜单中
        LinkedHashMap<String,List<TbMenu>> menuMap = new LinkedHashMap<>();
        for (int i=0;i<menutblsonList.size();i++) {
            List<TbMenu> menutblList = new ArrayList<>();
            for (int j=0;j<menutblList1.size();j++){
                if (menutblsonList.get(i).getMenuId()==menutblList1.get(j).getMenuPid()){
                    menutblList.add(menutblList1.get(j));
                }
            }
            menuMap.put(menutblsonList.get(i).getMenuName(),menutblList);
        }
        System.out.println("menuMap="+menuMap);
        return menuMap;
    }

}
