package com.cnzk.mapper;

import com.cnzk.pojo.Admin;
import com.cnzk.pojo.TbRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface RoleMapper {
    int queryRoleCount();
    //	查询管理员，包括带条件及分页
    List<TbRole> queryRole(@Param("start") int start, @Param("pageSize") int pageSize);
}
