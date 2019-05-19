package com.fushan.service.user;
import com.fushan.entity.UserInfo;
import com.fushan.service.BaseService;
public interface UserInfoService extends BaseService<UserInfo> {
    UserInfo userCheck(String userName, String password);
    int deleteByPrimaryKey(Integer key);
    UserInfo selectByPrimaryKey(Integer key);
}
