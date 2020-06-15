package com.cnzk.mapper;

import com.cnzk.pojo.TbParam;
import com.cnzk.pojo.TbRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ParamMapper {
    int queryParamCount();
    List<TbParam> queryParam(@Param("start") int start, @Param("pageSize") int pageSize);
    int editParam(TbParam param);
}
