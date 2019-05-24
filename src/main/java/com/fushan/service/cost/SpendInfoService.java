package com.fushan.service.cost;
import com.fushan.entity.SpendInfo;
import com.fushan.service.BaseService;
public interface SpendInfoService extends BaseService<SpendInfo> {
    int deleteByPrimaryKey(Integer key);
    SpendInfo selectByPrimaryKey(Integer key);
    int insertSelective(SpendInfo entity);
    int updateByPrimaryKey(SpendInfo entity);
}
