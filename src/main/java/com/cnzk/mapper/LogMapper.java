package com.cnzk.mapper;

import com.cnzk.pojo.TbLog;
import com.cnzk.pojo.TbRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface LogMapper {
    int queryLogCount();
    //	查询角色，
    List<TbLog> queryLog(Map map);

    int addLog(TbLog log);
}
