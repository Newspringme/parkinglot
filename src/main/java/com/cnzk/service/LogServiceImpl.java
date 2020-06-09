package com.cnzk.service;

import com.cnzk.mapper.LogMapper;
import com.cnzk.mapper.RoleMapper;
import com.cnzk.pojo.LayuiData;
import com.cnzk.pojo.TbLog;
import com.cnzk.pojo.TbRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
@Service
public class LogServiceImpl  implements LogService{
    @Resource
    private LogMapper logMapper;

    @Override
    public LayuiData queryLog(String page,String limit,String username ,String startTime,String endTime) {
        int startPage=Integer.parseInt(page);//获取页码;
        int pageSize=Integer.parseInt(limit);//每页数量
        int start = (startPage-1)*pageSize;//计算出起始查询位置
        HashMap<String, Object> map = new HashMap<>();
        map.put("start",start);
        map.put("pageSize",pageSize);
        map.put("username",username);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        List<TbLog> list=logMapper.queryLog(map);
        int count=logMapper.queryLogCount();
        LayuiData layuiData=new LayuiData();
        layuiData.setCode(0);
        layuiData.setCount(count);
        layuiData.setData(list);
        return layuiData;
    }

    @Override
    public int addLog(TbLog log) {
        return logMapper.addLog(log);
    }
}
