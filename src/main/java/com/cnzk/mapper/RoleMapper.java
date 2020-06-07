package com.cnzk.mapper;


import com.cnzk.pojo.TbRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
@Mapper
public interface RoleMapper {
    int queryRoleCount();
    //	查询角色，包括带条件及分页
    List<TbRole> queryRole(@Param("start") int start, @Param("pageSize") int pageSize);

    void addRole(@Param("roleName") String roleName);

    void deleteRole(TbRole role);

    void editRole(TbRole role);

    //根据角色id查角色等级
    public TbRole querySort(Integer roleId);

    //查当前用户所能修改角色的权限的角色集合
    public List<TbRole> queryRolesList(HashMap<String, Object> condition);

    //角色集合数量
    public Integer queryCount(HashMap<String, Object> condition);

}
