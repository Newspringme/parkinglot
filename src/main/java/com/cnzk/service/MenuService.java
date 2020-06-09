package com.cnzk.service;

import com.cnzk.pojo.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * @author LQ
 */
public interface MenuService {
//    登录菜单
    Map<String, Object> menu(Integer roleId);
//    修改权限显示所有菜单
    List<MenuVo> menuAuthority(Integer roleId);

}
