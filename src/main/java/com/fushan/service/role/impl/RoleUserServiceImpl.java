package com.fushan.service.role.impl;
import com.fushan.entity.RoleUser;
import com.fushan.mapper.RoleUserMapper;
import com.fushan.service.role.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleUserServiceImpl implements RoleUserService {
    @Autowired
    RoleUserMapper roleUserMapper;
    @Override
    public int insertSelective(RoleUser entity) {
        return roleUserMapper.insertSelective(entity);
    }

    @Override
    public int deleteByUserId(Integer id) {
        return roleUserMapper.deleteByUserId(id);
    }
}
