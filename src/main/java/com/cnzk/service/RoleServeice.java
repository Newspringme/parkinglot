package com.cnzk.service;

import com.cnzk.pojo.LayuiData;
import com.cnzk.pojo.TbRole;

public interface RoleServeice {
    LayuiData queryRole( int start, int pageSize);
    int addRole(TbRole role);
    int delecteRole(TbRole role);
    int editRole(TbRole role);
}
