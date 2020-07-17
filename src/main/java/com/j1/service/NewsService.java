package com.j1.service;

import com.j1.pojo.Member;
import com.j1.pojo.News;

/**
 * Created by wangchuanfu on 20/7/15.
 */
public interface NewsService {

    News selectNewsInfo(Long id);

    Member getMemberById(Long id);
}
