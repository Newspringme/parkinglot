package com.cnzk.service;

import com.cnzk.pojo.LayuiData;

public interface LogService {

    LayuiData queryLog(int start, int pageSize);
}
