package com.cnzk.service;

import com.cnzk.pojo.LayuiData;
import com.cnzk.pojo.TbLog;

public interface LogService {

    LayuiData queryLog(String page,String limit,String username ,String startTime,String endTime);

    int addLog(TbLog log);
}
