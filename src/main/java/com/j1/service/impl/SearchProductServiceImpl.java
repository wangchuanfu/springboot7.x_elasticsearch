package com.j1.service.impl;


import com.j1.service.InitIndexService;
import com.j1.service.SearchProductService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by wangchuanfu on 20/7/20.
 */
@Service
public class SearchProductServiceImpl implements SearchProductService {
   @Resource
   InitIndexService initIndexService;

    @Override
    public List<Map<String, Object>> querySearch(String keyword, Integer pageNo, Integer pageSize) {

        try {
            initIndexService.querySearch(keyword,pageNo,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public String querySearchGoods(String keyword, Integer pageNo, Integer pageSize) {

        return initIndexService.querySearchGoods(keyword,pageNo,pageSize);

    }
}