package com.j1.service;

import com.j1.result.ServiceMessage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * Created by wangchuanfu on 20/8/3.
 */
@Component
public interface SuggestionSearchService {

    public ServiceMessage<List<String> > querySuggest(String keyword);
}
