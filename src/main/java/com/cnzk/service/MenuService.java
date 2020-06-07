package com.cnzk.service;

import com.cnzk.pojo.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * @author LQ
 */
public interface MenuService {
    public LinkedHashMap<String, List<TbMenu>> findMenus(Integer rolesid);
    Map<String, Object> menu();
}
