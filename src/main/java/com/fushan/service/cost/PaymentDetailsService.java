package com.fushan.service.cost;
import com.fushan.entity.PaymentDetails;
import com.fushan.service.BaseService;

public interface PaymentDetailsService extends BaseService<PaymentDetails> {
    int deleteByPrimaryKey(Integer id);
    int insert(PaymentDetails record);
    int insertSelective(PaymentDetails record);
    PaymentDetails selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(PaymentDetails record);
    int updateByPrimaryKey(PaymentDetails record);
    int deleteByPaymentId(Integer id);
    double sumAmountByPyamentId(Integer id);
}
