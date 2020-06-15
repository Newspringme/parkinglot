package com.cnzk.service;

import com.cnzk.pojo.Admin;
import com.cnzk.pojo.Charge;

public interface FaceService {
    Admin findAdminByAccount(String adminnum);
    Charge findChargeByAccount(String adminnum);
}
