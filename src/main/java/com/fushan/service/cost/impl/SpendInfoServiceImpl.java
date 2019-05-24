package com.fushan.service.cost.impl;
import com.fushan.common.util.DataGrid;
import com.fushan.common.util.PageInfo;
import com.fushan.entity.SpendInfo;
import com.fushan.mapper.cost.SpendInfoMapper;
import com.fushan.service.cost.SpendInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SpendInfoServiceImpl implements SpendInfoService {
    @Autowired
    SpendInfoMapper spendInfoMapper;
    @Override
    public int deleteByPrimaryKey(Integer key) {
        return spendInfoMapper.deleteByPrimaryKey(key);
    }

    @Override
    public SpendInfo selectByPrimaryKey(Integer key) {
        return spendInfoMapper.selectByPrimaryKey(key);
    }

    @Override
    public int insertSelective(SpendInfo entity) {
        return spendInfoMapper.insertSelective(entity);
    }

    @Override
    public int updateByPrimaryKey(SpendInfo entity) {
        return spendInfoMapper.updateByPrimaryKey(entity);
    }

    @Override
    public PageInfo<SpendInfo> pagedQuery(DataGrid grid) {
        return this.pagedQueryByCondition(grid,new HashMap<>());
    }

    @Override
    public PageInfo<SpendInfo> pagedQueryByCondition(DataGrid grid, Map<String, Object> map) {
        int totalRows = spendInfoMapper.count(map);
        int startRows = (grid.getPageNum() - 1) * grid.getPageSize();
        map.put("start",startRows);
        map.put("end",grid.getPageSize());
        List<SpendInfo> list = spendInfoMapper.pagedQuery(map);
        return new PageInfo<>(startRows,totalRows,grid.getPageSize(),grid.getPageNum(),list);
    }

    @Override
    public int count(Map<String, Object> map) {
        return spendInfoMapper.count(map);
    }
}
