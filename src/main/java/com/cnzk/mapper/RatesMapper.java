package com.cnzk.mapper;

import com.cnzk.pojo.TbRates;
import org.apache.ibatis.annotations.Mapper;



import java.util.HashMap;
import java.util.List;
@Mapper
public interface RatesMapper {
    //查计费规则列表
    List<TbRates> queryRatesList(HashMap<String, Object> condition);

    //规则集合数量
    Integer queryCount(HashMap<String, Object> condition);

//    修改规则金额
    Integer editRates(TbRates tbRates);
}
