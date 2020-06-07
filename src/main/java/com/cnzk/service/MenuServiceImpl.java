package com.cnzk.service;

import com.cnzk.mapper.MenuMapper;
import com.cnzk.pojo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author LQ
 */
@Service
public class MenuServiceImpl implements MenuService {

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
                if (menutblsonList.get(i).getMenuId().equals(menutblList1.get(j).getMenuPid())){
                    menutblList.add(menutblList1.get(j));
                }
            }
            menuMap.put(menutblsonList.get(i).getMenuName(),menutblList);
        }
        System.out.println("menuMap="+menuMap);
        return menuMap;
    }
    @Override
    public Map<String, Object> menu() {
        Map<String, Object> map = new HashMap<>(16);
        Map<String,Object> home = new HashMap<>(16);
        Map<String,Object> logo = new HashMap<>(16);
        List<TbMenu> menuList = menuMapper.findAllMenuSon();
        List<MenuVo> menuInfo = new ArrayList<>();
        for (TbMenu e : menuList) {
            MenuVo menuVO = new MenuVo();
            menuVO.setId(e.getMenuId());
            menuVO.setPid(e.getMenuPid());
            menuVO.setHref(e.getMenuUrl());
            menuVO.setTitle(e.getMenuName());
            menuVO.setIcon("");
            menuVO.setTarget("_self");
            menuInfo.add(menuVO);
        }
        map.put("menuInfo", TreeUtil.toTree(menuInfo, 0L));
        home.put("title","首页");
        home.put("href","/page/welcome-1");//控制器路由,自行定义
        logo.put("title","后台管理系统");
        logo.put("image","/static/images/back.jpg");//静态资源文件路径,可使用默认的logo.png
        map.put("homeInfo", "{title: '首页',href: '/ruge-web-admin/page/welcome.html'}}");
        map.put("logoInfo", "{title: 'RUGE ADMIN',image: 'images/logo.png'}");
        return map;
    }
}
