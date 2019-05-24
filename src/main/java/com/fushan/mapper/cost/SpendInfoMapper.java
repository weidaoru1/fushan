package com.fushan.mapper.cost;

import com.fushan.entity.SpendInfo;

public interface SpendInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpendInfo record);

    int insertSelective(SpendInfo record);

    SpendInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpendInfo record);

    int updateByPrimaryKey(SpendInfo record);
}