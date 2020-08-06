package com.j1.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.j1.pojo.EsAttribute;
import com.j1.service.InitIndexService;
import com.j1.service.IntoEsUtils;
import com.j1.service.SuggestionSearchService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeAction;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequestBuilder;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.DisMaxQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
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
@Slf4j
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
            List<String>  keywords = new ArrayList<>();

            //全拼前缀匹配
            CompletionSuggestionBuilder fullPinyinSuggest = new CompletionSuggestionBuilder("full_pinyin_suggest")
                    .prefix("full_pinyin").text(keyword).size(100);
            //汉字前缀匹配
            CompletionSuggestionBuilder suggestText = new CompletionSuggestionBuilder("suggestText")
                    .prefix("suggestText").text(keyword).size(100);
            //拼音搜字母前缀匹配
            CompletionSuggestionBuilder prefixPinyinSuggest = new CompletionSuggestionBuilder("prefix_pinyin_text")
                    .prefix("prefix_pinyin").text(keyword).size(100);


            /**
             * 中英文搜索提示
             *
             *
             *
             PUT /station_test/
             {
                 "settings": {
                 "index": {
                 "analysis": {
                 "analyzer": {
                 "pinyin_analyzer": {
                 "tokenizer": "my_pinyin"
                 }
             },
                 "tokenizer": {
                     "my_pinyin": {
                     "type": "pinyin",
                     "keep_first_letter":true,
                     "keep_separate_first_letter": true,
                     "keep_full_pinyin": true,
                     "keep_original": true,
                     "limit_first_letter_length": 16,
                     "lowercase": true
                            }
                       }
                    }
                }
             },
             "mappings": {

                     "properties": {
                     "station_name": {
                     "type": "text",
                     "analyzer": "ik_max_word",
                     "fields": {
                     "s-pinyin": {
                     "type": "completion",
                     "analyzer": "pinyin_analyzer"
                     }
                 }
             },
                 "station_code": {
                 "type": "completion"
                    }
                 }
              }

             }

             PUT /station_test/_doc/1
             {
             "station_code": "VAP",
             "station_name": "北京北"
             }

             PUT /station_test/_doc/2
             {
             "station_code": "BOP",
             "station_name": "北京东"
             }

             PUT /station_test/_doc/3
             {
             "station_code": "GGQ",
             "station_name": "广州南"
             }

             PUT /station_test/_doc/4
             {
             "station_code": "SHH",
             "station_name": "上海"
             }

             */
            //查询条件
            CompletionSuggestionBuilder stationName = SuggestBuilders.completionSuggestion("promptName.s-pinyin").prefix(keyword);
            CompletionSuggestionBuilder stationCode = SuggestBuilders.completionSuggestion("station_code").prefix(keyword);

            SearchRequest suggestSearchRequest = new SearchRequest().indices("station_test1").source(new SearchSourceBuilder().suggest(
                    new SuggestBuilder().addSuggestion("pinyin-suggest", stationName)
                           // .addSuggestion("code-suggest", stationCode)
            ));
            /**
             * GET station_test/_search
             {
                     "suggest": {
                     "code-suggest": {
                     "prefix": "bj",
                     "completion": {
                     "field": "station_code"
                     }
                 },
                     "pinyin-suggest": {
                     "prefix": "bj",
                     "completion": {
                     "field": "station_name.s-pinyin"
                       }
                     }
               }
             }
             */

            log.error(suggestSearchRequest.source().toString());

            SearchResponse suggestResponse = client.search(suggestSearchRequest, RequestOptions.DEFAULT);
            Suggest suggestResult = suggestResponse.getSuggest();










            SuggestBuilder suggestBuilder = new SuggestBuilder();
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            SearchRequest searchRequest = new SearchRequest(esAttribute.getSuggestIndexName());
           // SearchRequest searchRequest = new SearchRequest("station_test1");

            SuggestionBuilder suggestionBuilder = SuggestBuilders.completionSuggestion("promptName").prefix(keyword);
            suggestBuilder.addSuggestion("pinyin-suggest", suggestionBuilder);
            searchSourceBuilder.suggest(suggestBuilder);
            searchRequest.source(searchSourceBuilder);
            log.error(searchSourceBuilder.toString());
            /**
             *
             */
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

            Suggest suggest = searchResponse.getSuggest();
            if (suggest != null) {

                List<? extends Suggest.Suggestion.Entry<? extends Suggest.Suggestion.Entry.Option>> entries =
                        suggest.getSuggestion("pinyin-suggest").getEntries();

                for (Suggest.Suggestion.Entry<? extends Suggest.Suggestion.Entry.Option> entry : entries) {
                    for (Suggest.Suggestion.Entry.Option option : entry.getOptions()) {
                        /** 最多返回9个推荐，每个长度最大为20 */
                        String keyword1 = option.getText().string();
                        if (!StringUtils.isEmpty(keyword) && keyword.length() <= 20) {
                            /** 去除输入字段 */
                            if (keyword1.equals(keyword))
                                continue;
                            keywords.add(keyword1);
                            /**
                             *  if (keywords.size() >= 9) {
                             break;
                             }
                             */

                        }
                    }
                }
            }
            if(suggestResult!=null){


                List<? extends Suggest.Suggestion.Entry<? extends Suggest.Suggestion.Entry.Option>> results = suggestResult.getSuggestion("pinyin-suggest").getEntries();
                for (Suggest.Suggestion.Entry<? extends Suggest.Suggestion.Entry.Option> op : results) {
                    List<? extends Suggest.Suggestion.Entry.Option> options = op.getOptions();
                    for (Suggest.Suggestion.Entry.Option option : options) {
                        System.out.println( option.getText());
                        /** 最多返回9个推荐，每个长度最大为20 */
                        String keyword1 = option.getText().string();
                        if (!StringUtils.isEmpty(keyword) && keyword.length() <= 20) {
                            /** 去除输入字段 */
                            if (keyword1.equals(keyword))
                                continue;
                            keywords.add(keyword1);
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
