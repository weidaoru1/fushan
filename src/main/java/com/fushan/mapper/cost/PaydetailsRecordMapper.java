package com.fushan.mapper.cost;
import com.fushan.entity.PaydetailsRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface PaydetailsRecordMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(PaydetailsRecord record);
    int insertSelective(PaydetailsRecord record);
    PaydetailsRecord selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(PaydetailsRecord record);
    int updateByPrimaryKey(PaydetailsRecord record);
    int count(Map<String, Object> map);
    List<PaydetailsRecord> pagedQuery(Map<String, Object> map);
    int deleteByDetailsId(Integer id);
}