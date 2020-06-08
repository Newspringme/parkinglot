package com.cnzk.service;

import com.cnzk.pojo.LayuiData;
import com.cnzk.pojo.TbLog;

public interface LogService {

    LayuiData queryLog(int start, int pageSize);

    int addLog(TbLog log);
}
