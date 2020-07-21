package com.j1.service;

import java.util.List;
import java.util.Map;

/**
 * Created by wangchuanfu on 20/7/20.
 */
public interface SearchProductService {
    //查询
    public List<Map<String, Object>>  querySearch(String keyword, Integer pageNo, Integer pageSize);

    String querySearchGoods(String keyword, Integer pageNo, Integer pageSize);
}
