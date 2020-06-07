package com.cnzk.service;

import com.cnzk.pojo.LayuiData;
import com.cnzk.pojo.TbRole;

public interface RoleServeice {
    LayuiData queryRole( int start, int pageSize);
    void addRole(TbRole role);
    void delecteRole(TbRole role);
    void editRole(TbRole role);
}
