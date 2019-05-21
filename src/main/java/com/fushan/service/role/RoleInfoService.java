package com.fushan.service.role;

import com.fushan.entity.RoleInfo;
import java.util.List;
public interface RoleInfoService{
    List<RoleInfo> queryByUserId(Integer id);
    List<RoleInfo> queryListAll();
}
