package com.cnzk.mapper;


import com.cnzk.pojo.TbMenu;
import com.cnzk.pojo.TbRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
@Mapper
public interface RoleMapper {
    int queryRoleCount();
    //	查询角色
    List<TbRole> queryRole(@Param("start") int start, @Param("pageSize") int pageSize);

    int addRole(TbRole role);

    int deleteRole(TbRole role);

    int editRole(TbRole role);

    //根据角色id查角色等级
    TbRole querySort(Integer roleId);

    //查当前用户所能修改角色的权限的角色集合
    List<TbRole> queryRolesList(HashMap<String, Object> condition);

    //角色集合数量
    Integer queryCount(HashMap<String, Object> condition);

    //删除所选角色的角色菜单关联表里的所有菜单
    Integer deleRolesMenu(Integer rolesid);

    //重新添加所选角色的角色菜单
    Integer addRolesMenu(List<TbMenu> menutblList);


}
