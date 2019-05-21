package com.fushan.service.role;

import com.fushan.entity.RoleUser;

public interface RoleUserService {
    int insertSelective(RoleUser entity);
    int deleteByUserId(Integer id);
}
