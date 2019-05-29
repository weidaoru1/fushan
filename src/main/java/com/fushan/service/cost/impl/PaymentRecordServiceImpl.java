package com.fushan.service.cost.impl;
import com.fushan.common.util.DataGrid;
import com.fushan.common.util.PageInfo;
import com.fushan.entity.PaymentRecord;
import com.fushan.mapper.cost.PaymentRecordMapper;
import com.fushan.service.cost.PaymentRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentRecordServiceImpl implements PaymentRecordService {
    @Autowired
    PaymentRecordMapper paymentRecordMapper;

    @Override
    public int deleteByPaymentId(Integer id) {
        return paymentRecordMapper.deleteByPaymentId(id);
    }

    @Override

    public int deleteByPrimaryKey(Integer id) {
        return paymentRecordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PaymentRecord record) {
        return paymentRecordMapper.insert(record);
    }

    @Override
    public int insertSelective(PaymentRecord record) {
        return paymentRecordMapper.insertSelective(record);
    }

    @Override
    public PaymentRecord selectByPrimaryKey(Integer id) {
        return paymentRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PaymentRecord record) {
        return paymentRecordMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(PaymentRecord record) {
        return paymentRecordMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<PaymentRecord> pagedQuery(DataGrid grid) {
        return this.pagedQueryByCondition(grid,new HashMap<>());
    }

    @Override
    public PageInfo<PaymentRecord> pagedQueryByCondition(DataGrid grid, Map<String, Object> map) {
        int totalRows = paymentRecordMapper.count(map);
        int startRows = (grid.getPageNum() - 1) * grid.getPageSize();
        map.put("start",startRows);
        map.put("end",grid.getPageSize());
        List<PaymentRecord> list = paymentRecordMapper.pagedQuery(map);
        return new PageInfo<>(startRows,totalRows,grid.getPageSize(),grid.getPageNum(),list);
    }
}
