package com.j1.dao;

import com.j1.pojo.SuggestPrompt;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangchuanfu on 20/8/3.
 */

@Repository
public interface SuggestIndexMapper {

    List<SuggestPrompt> getAllSuggestProduct();
}
