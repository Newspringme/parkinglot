package com.cnzk.service;

import com.alibaba.fastjson.JSON;
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
    public Map<String, Object> menu(Integer roleId) {
        Map<String, Object> map = new HashMap<>(16);
        Map<String,Object> home = new HashMap<>(16);
        Map<String,Object> logo = new HashMap<>(16);
        List<TbMenu> menuList = menuMapper.queryRoleAllMenu(roleId);
        List<MenuVo> menuInfo = new ArrayList<>();
        for (TbMenu e : menuList) {
            MenuVo menuVO = new MenuVo();
            menuVO.setId(e.getMenuId());
            menuVO.setPid(e.getMenuPid());
            menuVO.setHref(e.getMenuUrl());
            menuVO.setTitle(e.getMenuName());
            menuVO.setIcon(e.getMenuIcon());
            menuVO.setTarget(e.getMeniTarget());
            menuInfo.add(menuVO);
        }
        map.put("menuInfo", TreeUtil.toTree(menuInfo, 0L));
        home.put("title","首页");
        home.put("href","/page/welcome-1");//控制器路由,自行定义
        logo.put("title","后台管理系统");
        logo.put("image","/static/img/back.jpg");//静态资源文件路径,可使用默认的logo.png
        map.put("homeInfo", "{title: '首页',href: '/ruge-web-admin/page/welcome.html'}}");
        map.put("logoInfo", "{title: 'RUGE ADMIN',image: '/static/img/logo.png'}");
        System.out.println(JSON.toJSON(map));
        return map;
    }

    @Override
    public List<MenuVo> menuAuthority(Integer roleId) {
        List<TbMenu> menuList1 = menuMapper.queryAllMenu();
        List<TbMenu> menuList = menuMapper.queryHaveMenu(roleId);
        List<MenuVo> menuInfo = new ArrayList<>();
        for (TbMenu e : menuList1) {
            MenuVo menuVO = new MenuVo();
            menuVO.setId(e.getMenuId());
            menuVO.setPid(e.getMenuPid());
            menuVO.setHref(e.getMenuUrl());
            menuVO.setTitle(e.getMenuName());
            menuVO.setIcon(e.getMenuIcon());
            menuVO.setTarget(e.getMeniTarget());
            menuVO.setSpread(true);
            for (int i=0;i<menuList.size();i++){
                if (e.getMenuId()==menuList.get(i).getMenuId()){
                    menuVO.setChecked(true);
                    break;
                }
            }
            menuInfo.add(menuVO);
        }
        return TreeUtil.toAuthoryTree(menuInfo, 0L);
    }
}
