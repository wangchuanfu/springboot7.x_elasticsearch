package com.j1.service.impl;

import com.j1.dao.NewsMapper;
import com.j1.pojo.Member;
import com.j1.pojo.News;
import com.j1.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wangchuanfu on 20/7/15.
 */
@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;


    @Override
    public News selectNewsInfo(Long id) {
        return newsMapper.selectNewsInfo(id);
    }

    @Override
    public Member getMemberById(Long id) {
        return newsMapper.getMemberById(id);
    }
}
