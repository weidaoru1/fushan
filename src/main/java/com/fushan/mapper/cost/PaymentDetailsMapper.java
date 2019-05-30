package com.fushan.mapper.cost;
import com.fushan.entity.PaymentDetails;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;
@Mapper
public interface PaymentDetailsMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(PaymentDetails record);
    int insertSelective(PaymentDetails record);
    PaymentDetails selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(PaymentDetails record);
    int updateByPrimaryKey(PaymentDetails record);
    int count(Map<String, Object> map);
    List<PaymentDetails> pagedQuery(Map<String, Object> map);
    int deleteByPaymentId(Integer id);
    double sumAmountByPyamentId(Integer id);
    List<PaymentDetails> queryListByPaymentId(Integer id);
}