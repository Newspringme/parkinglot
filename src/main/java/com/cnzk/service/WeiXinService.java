package com.cnzk.service;

import com.cnzk.pojo.TbFeedback;

import java.util.List;

public interface WeiXinService {
    List<String> queryImgUrl();
    Integer feedback(TbFeedback feedback);
}
