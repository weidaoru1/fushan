package com.fushan.service.role.impl;
import com.fushan.entity.RoleInfo;
import com.fushan.mapper.role.RoleInfoMapper;
import com.fushan.service.role.RoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleInfoServiceImpl implements RoleInfoService {
    @Autowired
    RoleInfoMapper roleInfoMapper;
    @Override
    public List<RoleInfo> queryListAll() {
        return roleInfoMapper.queryListAll();
    }

    @Override
    public List<RoleInfo> queryByUserId(Integer id) {
        Map<String,Object> map = new HashMap<>();
        map.put("userId",id);
        return roleInfoMapper.queryByUserId(map);
    }
}
