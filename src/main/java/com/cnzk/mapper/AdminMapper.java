package com.cnzk.mapper;

import com.cnzk.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author su
 * @date 2020/6/5-15:29
 */
@Mapper
public interface AdminMapper
{
    //	查询管理员记录数，包括带条件
    int queryAdminCount(Admin admin);

    //	查询管理员，包括带条件及分页
    List<Admin> queryAdmin(@Param("admin") Admin admin, @Param("start") int start, @Param("pageSize") int pageSize);


}
