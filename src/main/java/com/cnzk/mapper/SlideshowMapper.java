package com.cnzk.mapper;

import com.cnzk.pojo.TbSlideshow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
@Mapper
public interface SlideshowMapper {
    List<String> queryImgUrl(@Param("time") String time);

    List<TbSlideshow> querySlideShow(int start, int pageSize);
    Integer querySlideShowCount();
    Integer addSlideShow(TbSlideshow tbSlideshow);
    Integer deleteSlideShow(TbSlideshow tbSlideshow);
    Integer editSlideShow(TbSlideshow tbSlideshow);
}
