package com.cnzk.mapper;

import com.cnzk.pojo.TbFeedback;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FeedbackMapper {
    Integer feedback(TbFeedback feedback);
    Integer queryfeedbackCount();
    List<TbFeedback> queryfeedback(@Param("start") int start, @Param("pageSize") int pageSize);
}
