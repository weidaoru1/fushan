package com.fushan.service.cost.impl;

import com.fushan.common.util.DataGrid;
import com.fushan.common.util.PageInfo;
import com.fushan.entity.PaymentDetails;
import com.fushan.entity.PaymentInfo;
import com.fushan.mapper.cost.PaymentDetailsMapper;
import com.fushan.mapper.cost.PaymentInfoMapper;
import com.fushan.service.cost.PaymentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class PaymentInfoServiceImpl implements PaymentInfoService {
    @Autowired
    PaymentInfoMapper paymentInfoMapper;
    @Autowired
    PaymentDetailsMapper paymentDetailsMapper;
    @Override
    public int deleteByPrimaryKey(Integer key) {
        return paymentInfoMapper.deleteByPrimaryKey(key);
    }

    @Override
    public PaymentInfo selectByPrimaryKey(Integer key) {
        return paymentInfoMapper.selectByPrimaryKey(key);
    }

    @Override
    public List<PaymentInfo> queryList(PaymentInfo entity) {
        return paymentInfoMapper.queryList(entity);
    }

    @Override
    public List<PaymentInfo> queryListAll() {
        return paymentInfoMapper.queryListAll();
    }

    @Override
    public int insertSelective(PaymentInfo entity) {
        int res = paymentInfoMapper.insertSelective(entity);
        PaymentDetails details = new PaymentDetails();
        details.setAmount(entity.getAmount());
        details.setContact(entity.getContact());
        details.setCustomerName(entity.getCustomerName());
        details.setPayee(entity.getPayee());
        details.setPaymentId(entity.getId());
        details.setPaymentTime(entity.getPaymentTime());
        details.setCreateTime(new Date());
        details.setRemark(entity.getRemark());
        paymentDetailsMapper.insertSelective(details);
        return res;
    }

    @Override
    public int updateByPrimaryKey(PaymentInfo entity) {
        return paymentInfoMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public PageInfo<PaymentInfo> pagedQuery(DataGrid grid) {
        return this.pagedQueryByCondition(grid,new HashMap<>());
    }

    @Override
    public PageInfo<PaymentInfo> pagedQueryByCondition(DataGrid grid, Map<String, Object> map) {
        int totalRows = paymentInfoMapper.count(map);
        int startRows = (grid.getPageNum() - 1) * grid.getPageSize();
        map.put("start",startRows);
        map.put("end",grid.getPageSize());
        List<PaymentInfo> list = paymentInfoMapper.pagedQuery(map);
        return new PageInfo<>(startRows,totalRows,grid.getPageSize(),grid.getPageNum(),list);
    }
}
