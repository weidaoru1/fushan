package com.fushan.mapper.role;
import com.fushan.entity.RoleUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleUserMapper {
    int insertSelective(RoleUser entity);
    int deleteByUserId(Integer id);
}
