package com.fushan.mapper.cost;
import com.fushan.entity.SpendInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface SpendInfoMapper {
    int count(Map<String, Object> map);
    List<SpendInfo> pagedQuery(Map<String, Object> map);
    int deleteByPrimaryKey(Integer id);
    int insert(SpendInfo record);
    int insertSelective(SpendInfo record);
    SpendInfo selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(SpendInfo record);
    int updateByPrimaryKey(SpendInfo record);
}