package com.fushan.service;
import com.fushan.common.util.DataGrid;
import com.fushan.common.util.PageInfo;

import java.util.List;
import java.util.Map;

public interface BaseService<T> {
    PageInfo<T> pagedQuery(DataGrid grid);
    PageInfo<T> pagedQueryByCondition(DataGrid grid, Map<String, Object> map);
    int count(Map<String, Object> map);
}
