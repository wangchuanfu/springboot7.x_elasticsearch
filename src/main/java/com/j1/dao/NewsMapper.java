package com.j1.dao;

import com.j1.pojo.Member;
import com.j1.pojo.News;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by wangchuanfu on 20/7/15.
 */
@Mapper
@Repository
public interface NewsMapper {
    News selectNewsInfo(Long id);

    Member getMemberById(Long id);
}
