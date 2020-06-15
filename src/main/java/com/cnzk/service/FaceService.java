package com.cnzk.service;


import com.cnzk.pojo.Admin;
import com.cnzk.pojo.Charge;

public interface FaceService
{


    //通过管理员账户,查询管理员信息
    public Admin findAdminByAccount(String adminAccount);


    //通过收费员账户,查询收费员信息
    public Charge findChargeByAccount(String cashierAccount);

}