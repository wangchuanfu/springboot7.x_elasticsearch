package com.j1.service.impl;

import com.j1.service.CommentsNestedQueryService;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class CommentsNestedQueryServiceImpl  implements CommentsNestedQueryService {
    private static final String INDEX = "nested_index";
    @Resource
    @Qualifier("restHighLevelClient")
    public RestHighLevelClient client;
    @Override
    public void getCommments() {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        SearchRequest searchRequest = new SearchRequest(INDEX);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //嵌套查询
        //嵌套查询bool--动态设置值
        BoolQueryBuilder nestedBoolQuery = null;
        nestedBoolQuery = QueryBuilders.boolQuery();
        nestedBoolQuery.should(QueryBuilders.termQuery("comments.age", 31)).should(QueryBuilders.termQuery("comments.name", "张三"));
        boolQueryBuilder.must(QueryBuilders.nestedQuery("comments", nestedBoolQuery, ScoreMode.None));
        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            //循环遍历
            SearchHit[] searchHits = searchResponse.getHits().getHits();
            SearchHits hits = searchResponse.getHits();
            long totalHits = hits.getTotalHits().value;
            System.out.println(totalHits);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}