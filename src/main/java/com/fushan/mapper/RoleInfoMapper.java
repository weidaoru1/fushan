package com.fushan.mapper;
import com.fushan.entity.RoleInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoleInfoMapper {
    List<RoleInfo> queryByUserId(Map<String, Object> map);
    List<RoleInfo> queryListAll();
}
