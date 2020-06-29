package com.cnzk.controller;

public interface UserConstantInterface {

    // 请求的网址
    public static final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";
    // 你的appid
    public static final String WX_LOGIN_APPID = "wxa040674268427cbd";
    // 你的密匙
    public static final String WX_LOGIN_SECRET = "4be6e8a006e0bd9bb2ce37a93751b45b";
    // 固定参数
    public static final String WX_LOGIN_GRANT_TYPE = "authorization_code";
}
