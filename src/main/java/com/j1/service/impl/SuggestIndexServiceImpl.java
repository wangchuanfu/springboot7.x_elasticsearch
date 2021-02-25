package com.j1.service.impl;

import com.j1.dao.SuggestIndexMapper;
import com.j1.pojo.SuggestPrompt;
import com.j1.service.SuggestIndexService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangchuanfu on 20/8/3.
 */
@Service
//搜索提示词
public class SuggestIndexServiceImpl implements SuggestIndexService {

    @Resource
    SuggestIndexMapper suggestIndexMapper;

    //获取搜索提示词
    @Override
    public List<SuggestPrompt> getAllSuggestProduct() {

        List<SuggestPrompt> allProduct = suggestIndexMapper.getAllSuggestProduct();

        return allProduct;
    }
}
