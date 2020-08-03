package com.j1.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.j1.pojo.EsAttribute;
import com.j1.service.InitIndexService;
import com.j1.service.IntoEsUtils;
import com.j1.service.SuggestionSearchService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.SuggestionBuilder;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.elasticsearch.search.suggest.phrase.PhraseSuggestionBuilder;
import org.elasticsearch.search.suggest.term.TermSuggestionBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wangchuanfu on 20/8/3.
 */
@Service
//提示词搜索
public class SuggestionSearchServiceImpl implements SuggestionSearchService {
    @Resource
    InitIndexService initIndexService;

    @Resource
    IntoEsUtils intoEsUtils;
    @Resource
    @Qualifier("restHighLevelClient")
    public RestHighLevelClient client;
    @Resource
    EsAttribute esAttribute;
    @Resource
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public List<String> querySuggest(String keyword) {
        try {


            SuggestBuilder suggestBuilder = new SuggestBuilder();
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            SearchRequest searchRequest = new SearchRequest(esAttribute.getSuggestIndexName());

            SuggestionBuilder suggestionBuilder = SuggestBuilders.completionSuggestion("promptName").prefix(keyword);
            suggestBuilder.addSuggestion("my_index_suggest", suggestionBuilder);
            searchSourceBuilder.suggest(suggestBuilder);
            searchRequest.source(searchSourceBuilder);


            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            List<String> keywords = null;
            Suggest suggest = searchResponse.getSuggest();
            if (suggest != null) {
                keywords = new ArrayList<>();
                List<? extends Suggest.Suggestion.Entry<? extends Suggest.Suggestion.Entry.Option>> entries =
                        suggest.getSuggestion("my_index_suggest").getEntries();

                for (Suggest.Suggestion.Entry<? extends Suggest.Suggestion.Entry.Option> entry : entries) {
                    for (Suggest.Suggestion.Entry.Option option : entry.getOptions()) {
                        /** 最多返回9个推荐，每个长度最大为20 */
                        String keyword1 = option.getText().string();
                        if (!StringUtils.isEmpty(keyword) && keyword.length() <= 20) {
                            /** 去除输入字段 */
                            if (keyword1.equals(keyword))
                                continue;
                            keywords.add(keyword1);
                            if (keywords.size() >= 9) {
                                break;
                            }
                        }
                    }
                }
            }
          return keywords;
            } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
