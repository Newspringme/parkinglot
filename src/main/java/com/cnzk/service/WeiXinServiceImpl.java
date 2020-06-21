package com.cnzk.service;

import com.cnzk.mapper.SlideshowMapper;
import com.cnzk.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class WeiXinServiceImpl implements WeiXinService{
    @Resource
    private SlideshowMapper slideshowMapper;


    @Override
    public List<String> queryImgUrl() {
        Date date = new Date();
        //使用UUID+后缀名保存文件名，防止中文乱码问题
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String time = simpleDateFormat.format(date);
        return slideshowMapper.queryImgUrl(time);
    }
}
