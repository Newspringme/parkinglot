package com.cnzk.service;

import com.cnzk.mapper.LogMapper;
import com.cnzk.mapper.RoleMapper;
import com.cnzk.pojo.LayuiData;
import com.cnzk.pojo.TbLog;
import com.cnzk.pojo.TbRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class LogServiceImpl  implements LogService{
    @Resource
    private LogMapper logMapper;

    @Override
    public LayuiData queryLog(int start, int pageSize) {
        List<TbLog> list=logMapper.queryLog(start, pageSize);
        int count=logMapper.queryLogCount();
        LayuiData layuiData=new LayuiData();
        layuiData.setCode(0);
        layuiData.setCount(count);
        layuiData.setData(list);
        return layuiData;
    }
}
