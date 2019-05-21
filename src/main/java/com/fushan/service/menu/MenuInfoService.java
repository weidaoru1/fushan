package com.fushan.service.menu;
import com.fushan.entity.MenuInfo;
import com.fushan.service.BaseService;

import java.util.List;

public interface MenuInfoService extends BaseService<MenuInfo> {
    List<MenuInfo> queryByUserId(Integer id);
    MenuInfo selectByPrimaryKey(Integer key);
    List<MenuInfo> queryList(MenuInfo entity);
    List<MenuInfo> queryListAll();
    int insertSelective(MenuInfo entity);
    int updateByPrimaryKey(MenuInfo entity);
}
