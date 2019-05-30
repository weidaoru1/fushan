package com.fushan.service.cost.impl;
import com.fushan.common.util.DataGrid;
import com.fushan.common.util.PageInfo;
import com.fushan.entity.PaymentDetails;
import com.fushan.mapper.cost.PaymentDetailsMapper;
import com.fushan.service.cost.PaymentDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentDetailsServiceImpl implements PaymentDetailsService {
    @Autowired
    PaymentDetailsMapper paymentDetailsMapper;

    @Override
    public int deleteByPaymentId(Integer id) {
        return paymentDetailsMapper.deleteByPaymentId(id);
    }

    @Override
    public List<PaymentDetails> queryListByPaymentId(Integer id) {
        return paymentDetailsMapper.queryListByPaymentId(id);
    }

    @Override

    public int deleteByPrimaryKey(Integer id) {
        return paymentDetailsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PaymentDetails record) {
        return paymentDetailsMapper.insert(record);
    }

    @Override
    public int insertSelective(PaymentDetails record) {
        return paymentDetailsMapper.insertSelective(record);
    }

    @Override
    public PaymentDetails selectByPrimaryKey(Integer id) {
        return paymentDetailsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PaymentDetails record) {
        return paymentDetailsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(PaymentDetails record) {
        return paymentDetailsMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<PaymentDetails> pagedQuery(DataGrid grid) {
        return this.pagedQueryByCondition(grid,new HashMap<>());
    }

    @Override
    public PageInfo<PaymentDetails> pagedQueryByCondition(DataGrid grid, Map<String, Object> map) {
        int totalRows = paymentDetailsMapper.count(map);
        int startRows = (grid.getPageNum() - 1) * grid.getPageSize();
        map.put("start",startRows);
        map.put("end",grid.getPageSize());
        List<PaymentDetails> list = paymentDetailsMapper.pagedQuery(map);
        return new PageInfo<>(startRows,totalRows,grid.getPageSize(),grid.getPageNum(),list);
    }
    @Override
    public double sumAmountByPyamentId(Integer id) {
        return paymentDetailsMapper.sumAmountByPyamentId(id);
    }
}
