package com.j1.action;

import com.j1.service.SuggestionSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

/**
 * Created by wangchuanfu on 20/8/3.
 */
@RestController
public class SuggestionSearchAction {

    //搜索提示
    @Autowired
    SuggestionSearchService suggestionSearchService;
    @ResponseBody
    @RequestMapping(value = "/searchSuggestion/{keyword}")

    public List<String> setSuggestionSearchService(HttpServletRequest request,
                                                   HttpServletResponse response, @PathVariable String keyword ) {

        List<String> strings = suggestionSearchService.querySuggest(keyword);
        return strings;

    }
}
