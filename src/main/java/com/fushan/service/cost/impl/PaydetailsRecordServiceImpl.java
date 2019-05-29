package com.fushan.service.cost.impl;

import com.fushan.common.util.DataGrid;
import com.fushan.common.util.PageInfo;
import com.fushan.entity.PaydetailsRecord;
import com.fushan.mapper.cost.PaydetailsRecordMapper;
import com.fushan.service.cost.PaydetailsRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaydetailsRecordServiceImpl implements PaydetailsRecordService {
    @Autowired
    PaydetailsRecordMapper paydetailsRecordMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return paydetailsRecordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PaydetailsRecord record) {
        return paydetailsRecordMapper.insert(record);
    }

    @Override
    public int insertSelective(PaydetailsRecord record) {
        return paydetailsRecordMapper.insertSelective(record);
    }

    @Override
    public PaydetailsRecord selectByPrimaryKey(Integer id) {
        return paydetailsRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PaydetailsRecord record) {
        return paydetailsRecordMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(PaydetailsRecord record) {
        return paydetailsRecordMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByDetailsId(Integer id) {
        return paydetailsRecordMapper.deleteByDetailsId(id);
    }

    @Override
    public PageInfo<PaydetailsRecord> pagedQuery(DataGrid grid) {
        return this.pagedQueryByCondition(grid,new HashMap<>());
    }

    @Override
    public PageInfo<PaydetailsRecord> pagedQueryByCondition(DataGrid grid, Map<String, Object> map) {
        int totalRows = paydetailsRecordMapper.count(map);
        int startRows = (grid.getPageNum() - 1) * grid.getPageSize();
        map.put("start",startRows);
        map.put("end",grid.getPageSize());
        List<PaydetailsRecord> list = paydetailsRecordMapper.pagedQuery(map);
        return new PageInfo<>(startRows,totalRows,grid.getPageSize(),grid.getPageNum(),list);
    }
}
