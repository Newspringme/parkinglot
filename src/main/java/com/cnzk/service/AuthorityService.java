package com.cnzk.service;

import com.cnzk.pojo.*;

import java.util.HashMap;
import java.util.List;

public interface AuthorityService {

//  可修改的角色列表
    public LayuiData queryRolesList(HashMap<String, Object> condition);

}
