package com.j1.action;

import com.j1.service.InitIndexService;
import com.j1.service.SuggestionSearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by wangchuanfu on 20/7/10.
 */

    @RestController
public class SearchGoodsAction {

    //搜索提示
    @Autowired
    SuggestionSearchService suggestionSearchService;
    @Resource
    InitIndexService initIndexService;
    //根据关键字搜索
    @RequestMapping("/search/{keyword}/{pageNo}/{pageSize}")
    public  List searchPage(@PathVariable("keyword") String keyword,
                            @PathVariable("pageNo") Integer pageNo,
                            @PathVariable("pageSize") Integer pageSize) throws  Exception{
        if(keyword==null){
            keyword="java";//设置默认关键字
        }
        List<Map<String, Object>> list = initIndexService.search(keyword, pageNo, pageSize);

        return list;

    }

    //搜索提示
    @RequestMapping("/search/suggest")
    public List<String> searchSuggest(@RequestParam(required = false) String keyword) {


        List<String> strings = suggestionSearchService.querySuggest(keyword);

        return strings;
    }
}
