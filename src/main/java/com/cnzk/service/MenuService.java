package com.cnzk.service;

import com.cnzk.pojo.*;

import java.util.LinkedHashMap;
import java.util.List;


/**
 * @author LQ
 */
public interface MenuService {
    public LinkedHashMap<String, List<TbMenu>> findMenus(Integer rolesid);
}
