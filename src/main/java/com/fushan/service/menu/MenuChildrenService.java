package com.fushan.service.menu;
import com.fushan.entity.MenuChildren;
import com.fushan.service.BaseService;

import java.util.List;

public interface MenuChildrenService extends BaseService<MenuChildren> {
    List<MenuChildren> queryMenuByParentId(Integer parentId);
}
