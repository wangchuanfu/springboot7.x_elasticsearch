package com.j1.service;


import com.j1.common.base.PageRequest;
import com.j1.common.base.Pageable;
import com.j1.common.base.Sort;
import com.j1.common.utils.BoolQueryBuilders;
import com.j1.pojo.EsAttribute;
import com.j1.type.ProductFieldEnum;
import com.j1.utils.DefaultIndexField;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.DocValueFormat;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Stats;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by wangchuanfu on 20/7/10.
 */
@Service
@Slf4j
public class InitIndexService {

    @Resource
    IntoEsUtils intoEsUtils;
    @Resource
    @Qualifier("restHighLevelClient")
    public RestHighLevelClient client;
    @Resource
    EsAttribute esAttribute;

    public List<Map<String, Object>> search(String keyword, Integer pageNo, Integer pageSize) throws Exception {
        return querySearchjd(keyword, pageNo, pageSize);
    }

    private List<Map<String, Object>> querySearchjd(String keyword, Integer pageNo, Integer pageSize) {
        try {
            //创建searchRequest
            SearchRequest searchRequest = new SearchRequest("jd_goods");
            //构建查询条件
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", keyword);//term 精确查找
            MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", keyword);//一般match查询
            searchSourceBuilder.query(matchQueryBuilder);


            //boolean 查询
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

            boolQueryBuilder.mustNot(QueryBuilders.termQuery("ecPrice", -1));

            searchSourceBuilder.postFilter(boolQueryBuilder);


            searchSourceBuilder.size(pageSize);
            searchSourceBuilder.from(pageNo);
            //显示高亮
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("title");
            highlightBuilder.preTags("<span style='color:red'>");
            highlightBuilder.postTags("</span>");

            searchSourceBuilder.highlighter(highlightBuilder);
            searchRequest.source(searchSourceBuilder);

            log.info(searchRequest.toString());
            //执行查询
            SearchResponse searchResponse = null;

            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

            //循环遍历
            SearchHit[] searchHits = searchResponse.getHits().getHits();
            List<Map<String, Object>> list = new ArrayList<>();
            for (SearchHit searchHit : searchHits) {
                Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();//查询的原来的结果
                Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
                HighlightField title = highlightFields.get("title");
                if (title != null) {
                    //解析高亮字段,将之前查出来的没高亮的字段 替换为高亮字段
                    Text[] fragments = title.fragments();
                    StringBuilder newTitle = new StringBuilder();
                    for (Text fragment : fragments) {
                        newTitle.append(fragment.string());
                    }
                    sourceAsMap.put("title", newTitle);
                }
                list.add(sourceAsMap);

            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    //根据关键字查询
    public List<Map<String, Object>> querySearch(String keyword, Integer pageNo, Integer pageSize) throws Exception {

        //创建searchRequest
        SearchRequest searchRequest = new SearchRequest("jd_goods");
        //构建查询条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //boolean 查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", keyword);//term 精确查找
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", keyword);//一般match查询


        boolQueryBuilder.mustNot(QueryBuilders.termQuery("title", -1));
        //过滤查询
        searchSourceBuilder.postFilter(boolQueryBuilder);
        searchSourceBuilder.query(termQueryBuilder);//term query
        searchSourceBuilder.query(boolQueryBuilder);


        searchSourceBuilder.query(matchQueryBuilder);//match query


        //模糊查询
        boolQueryBuilder.must(QueryBuilders.matchPhraseQuery("doctitle", ("*" + keyword + "*")));
        /**
         *

         // 时间段查询 start
         if(!StringUtil.isEmpty(createtime_begin)) {
         boolQueryBuilder.must(QueryBuilders.rangeQuery("createtime.keyword").gt(createtime_begin).includeLower(true));
         }
         if(!StringUtil.isEmpty(createtime_end)) {
         boolQueryBuilder.must(QueryBuilders.rangeQuery("createtime.keyword").lt(createtime_end).includeUpper(true));
         }  */
        searchSourceBuilder.trackTotalHits(true);


        searchSourceBuilder.timeout(TimeValue.timeValueSeconds(10));//查询时间
        searchSourceBuilder.size(pageSize);
        searchSourceBuilder.from(pageNo);
        //显示高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");

        //排序
        searchSourceBuilder.sort("price", SortOrder.fromString(Sort.DEFAULT_DIRECTION.toString()));
        searchSourceBuilder.highlighter(highlightBuilder);
        searchRequest.source(searchSourceBuilder);

        log.info(searchRequest.toString());
        //执行查询
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        //循环遍历
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        SearchHits hits = searchResponse.getHits();

        List<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit searchHit : searchHits) {
            Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();//查询的原来的结果
            Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
            HighlightField title = highlightFields.get("title");
            if (title != null) {
                //解析高亮字段,将之前查出来的没高亮的字段 替换为高亮字段
                Text[] fragments = title.fragments();
                StringBuilder newTitle = new StringBuilder();
                for (Text fragment : fragments) {
                    newTitle.append(fragment.string());
                }
                sourceAsMap.put("title", newTitle);
            }
            list.add(sourceAsMap);

        }
        return list;

    }

}
