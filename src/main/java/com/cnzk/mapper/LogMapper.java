package com.cnzk.mapper;

import com.cnzk.pojo.TbLog;
import com.cnzk.pojo.TbRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface LogMapper {
    int queryLogCount();
    //	查询角色，
    List<TbLog> queryLog(@Param("start") int start, @Param("pageSize") int pageSize);

    int addLog(TbLog log);
}
