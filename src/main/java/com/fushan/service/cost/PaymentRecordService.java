package com.fushan.service.cost;
import com.fushan.entity.PaymentRecord;
import com.fushan.service.BaseService;
public interface PaymentRecordService extends BaseService<PaymentRecord> {
    int deleteByPrimaryKey(Integer id);
    int insert(PaymentRecord record);
    int insertSelective(PaymentRecord record);
    PaymentRecord selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(PaymentRecord record);
    int updateByPrimaryKey(PaymentRecord record);
    int deleteByPaymentId(Integer id);
}
