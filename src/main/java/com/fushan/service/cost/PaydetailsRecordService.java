package com.fushan.service.cost;
import com.fushan.entity.PaydetailsRecord;
import com.fushan.service.BaseService;

public interface PaydetailsRecordService extends BaseService<PaydetailsRecord> {
    int deleteByPrimaryKey(Integer id);
    int insert(PaydetailsRecord record);
    int insertSelective(PaydetailsRecord record);
    PaydetailsRecord selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(PaydetailsRecord record);
    int updateByPrimaryKey(PaydetailsRecord record);
    int deleteByDetailsId(Integer id);
}
