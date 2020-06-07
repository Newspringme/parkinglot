package com.cnzk.mapper;

import com.cnzk.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author su
 * @date 2020/6/5-15:29
 */
@Mapper
public interface AdminMapper
{
    //	管理员登录
    Admin adminlogin(Map<String,Object> map);

    //	查询管理员记录数，包括带条件
    int queryAdminCount(Admin admin);

    //	查询管理员，包括带条件及分页
    List<Admin> queryAdmin(@Param("admin") Admin admin, @Param("start") int start, @Param("pageSize") int pageSize);


}
