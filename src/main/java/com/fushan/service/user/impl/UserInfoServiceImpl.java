package com.fushan.service.user.impl;
import com.fushan.common.util.*;
import com.fushan.entity.UserInfo;
import com.fushan.mapper.UserInfoMapper;
import com.fushan.service.user.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserInfoServiceImpl  implements UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public PageInfo<UserInfo> pagedQuery(DataGrid grid) {
        return this.pagedQueryByCondition(grid,new HashMap<>());
    }

    @Override
    public PageInfo<UserInfo> pagedQueryByCondition(DataGrid grid, Map<String, Object> map) {
        int totalRows = userInfoMapper.count(map);
        int startRows = (grid.getPageNum() - 1) * grid.getPageSize();
        map.put("start",startRows);
        map.put("end",grid.getPageSize());
        List<UserInfo> list = userInfoMapper.pagedQuery(map);
        return new PageInfo<>(startRows,totalRows,grid.getPageSize(),grid.getPageNum(),list);
    }

    @Override
    public List<UserInfo> queryList(UserInfo entity) {
        return userInfoMapper.queryList(entity);
    }

    @Override
    public List<UserInfo> queryListAll() {
        return userInfoMapper.queryListAll();
    }

    @Override
    public int count(Map<String, Object> map) {
        return userInfoMapper.count(map);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        if (RedisUtils.redisOpen()){
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            UserInfo userInfo = (UserInfo)redisTemplate.opsForValue().get(Constants.USER_KEY + id);
            if (userInfo != null){
                //删除掉登录用户部分
                redisTemplate.delete(Constants.LOGIN_KEY + userInfo.getUserName());
            }
            redisTemplate.delete(Constants.USER_KEY + id);
            return userInfoMapper.deleteByPrimaryKey(id);
        }
        return userInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public UserInfo selectByPrimaryKey(Integer id) {
        UserInfo userInfo = null;
        if (RedisUtils.redisOpen()){
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            userInfo = (UserInfo)redisTemplate.opsForValue().get(Constants.USER_KEY + id);
            if (userInfo == null){
                userInfo = userInfoMapper.selectByPrimaryKey(id);
                redisTemplate.opsForValue().set(Constants.USER_KEY  +  userInfo.getId(),userInfo);
            }
            return userInfo;
        }
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertSelective(UserInfo userInfo) {
        int  res = userInfoMapper.insertSelective(userInfo);
        if (RedisUtils.redisOpen()){
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.opsForValue().set(Constants.USER_KEY + userInfo.getId(),userInfo);
        }
        return res;
    }

    @Override
    public int updateByPrimaryKey(UserInfo userInfo) {
        if (RedisUtils.redisOpen()){
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.opsForValue().set(Constants.USER_KEY + userInfo.getId(),userInfo);
            //更新登录用户信息
            redisTemplate.opsForValue().set(Constants.LOGIN_KEY + userInfo.getUserName(),userInfo);
        }
        return  userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }

    @Override
    public UserInfo userCheck(String userName, String password) {
        Map<String,Object> map = new HashMap<>();
        map.put("userName",userName);
        map.put("password", MD5utils.encrypt(password));
        UserInfo userInfo = null;
        if (RedisUtils.redisOpen()){
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            //从缓存获取用户登录信息
            userInfo = (UserInfo)redisTemplate.opsForValue().get(Constants.LOGIN_KEY + userName);
            if (userInfo != null && MD5utils.encrypt(password).equals(userInfo.getPassword())){
                return userInfo;
            }
        }
        userInfo = userInfoMapper.userCheck(map);
        if (userInfo != null && RedisUtils.redisOpen()){
            redisTemplate.opsForValue().set(Constants.LOGIN_KEY + userName,userInfo);
        }
        return userInfo;
    }
}
