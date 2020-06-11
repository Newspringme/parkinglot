package com.cnzk.mapper;

import com.cnzk.pojo.TbCombo;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface ComboMapper {
    //查计费规则列表
    List<TbCombo> queryComboList(HashMap<String, Object> condition);

    //规则集合数量
    Integer queryCount(HashMap<String, Object> condition);

    //    新增月缴状态
    Integer addCombo(TbCombo tbCombo);

    //    修改月缴状态
    Integer updataCombo(TbCombo tbCombo);

    //    修改月缴产品
    Integer editCombo(TbCombo tbCombo);
}
