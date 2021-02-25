package com.j1.service.impl;

import com.j1.pojo.EsAttribute;
import com.j1.service.BucketAndMetricService;
import com.j1.service.IntoEsUtils;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Cancellable;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.term.TermSuggestion;
import org.elasticsearch.search.suggest.term.TermSuggestionBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * Created by wangchuanfu on 20/7/22.
 */
@Service
@Slf4j
public class BucketAndMetricServiceImpl implements BucketAndMetricService {

    @Resource
    IntoEsUtils intoEsUtils;
    @Resource
    @Qualifier("restHighLevelClient")
    public RestHighLevelClient client;
    @Resource
    EsAttribute esAttribute;


    //测试dateHistogramAggregation 聚合分组
    @Override
    public void dateHistogramAggregation() {
        try {

            /**
             *

             //模糊查询
             QueryBuilder queryBuilder = QueryBuilders.matchQuery("user", "kimchy").fuzziness(Fuzziness.AUTO).prefixLength(3).maxExpansions(10);
             //另一种模糊查询
             MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("user", "kimchy");
             matchQueryBuilder.fuzziness(Fuzziness.AUTO);
             matchQueryBuilder.prefixLength(3);
             matchQueryBuilder.maxExpansions(10);
             //不管是哪种查询,都要将QueryBuilder放入searchSourceBuilder中
             searchSourceBuilder.query(queryBuilder);
             searchSourceBuilder.fetchSource(false);//关闭source

             //排序
             searchSourceBuilder.sort( new ScoreSortBuilder().order(SortOrder.DESC));
             searchSourceBuilder.sort(new FieldSortBuilder("ecPrice").order(SortOrder.ASC));
             //执行查询
             SearchRequest searchRequest = new SearchRequest(esAttribute.getIndexName());

             SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
             */


            /**
             *

             //请求Suggestion
             SuggestBuilder suggestBuilder = new SuggestBuilder();
             TermSuggestionBuilder termSuggestionBuilder = SuggestBuilders.termSuggestion("productName").text("感冒");
             //添加suggestion构建器并将其命名为suggest_user。
             suggestBuilder.addSuggestion("suggest_product",termSuggestionBuilder);
             searchSourceBuilder.suggest(suggestBuilder) ;

             Suggest suggest = searchResponse.getSuggest();
             TermSuggestion termSuggestion = suggest.getSuggestion("suggest_product");
             //循环遍历
             for (TermSuggestion.Entry entry : termSuggestion.getEntries()) {
             for (TermSuggestion.Entry.Option  option :entry) {
             String text = option.getText().toString();
             }
             }
             */
            /**
             * 分析API可用于分析特定搜索请求的查询和聚合的执行情况，
             * 为了使用它，必须在SearchSourceBuilder上将profile标志设置为true
             */


            // searchSourceBuilder.profile(true);

            /**
             * 同步:
             *
             * 以下列方式执行SearchRequest时，
             * 客户端在继续执行代码之前等待返回SearchResponse：

             SearchResponse searchResponse1 = client.search(searchRequest, RequestOptions.DEFAULT);


             Aggregations aggregations = searchResponse.getAggregations();
             Terms group_by_color = aggregations.get("group_by_color");

             Terms.Bucket elastic = group_by_color.getBucketByKey("Elastic");

             Avg averageAge=elastic.getAggregations().get("avg_price");
             double value = averageAge.getValue();

             Map<String, Aggregation> aggregationMap = aggregations.getAsMap();

             Terms companyAggregation = (Terms)aggregationMap.get("group_by_color");


             /**
             * 异步:
             * 执行SearchRequest也可以以异步方式完成，
             * 以便客户端可以直接返回，用户需要通过将请求和监听器传递给异步搜索方法来指定响应或潜在的故障如何处理：
             */


            /**
             * GET /tvs/_search
             {
             "size": 0,
             "aggs": {
             "group_by_color": {
             "terms": {
             "field": "color"
             },
             "aggs": {
             "group_by_brand": {
             "terms": {
             "field": "brand"
             }
             },
             "group_by_price": {
             "terms": {
             "field": "price"
             }
             }
             }
             }
             }
             }
             */
            //聚合
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().size(0);
            //先bucket
            TermsAggregationBuilder aggregation = AggregationBuilders.terms("group_by_color").field("color");

            TermsAggregationBuilder termsAggregationBuilder = aggregation.subAggregation(AggregationBuilders.terms("group_by_brand").field("brand"));
            //metric
            termsAggregationBuilder.subAggregation(AggregationBuilders.avg("avg_price").field("price"));


            //为了增强代码的可读性,最好分开分段的写
            // aggregation.subAggregation(AggregationBuilders.terms("group_by_brand").field("brand").subAggregation(AggregationBuilders.avg("avg_price").field("price")));

            searchSourceBuilder.aggregation(termsAggregationBuilder);
            //执行查询
            SearchRequest searchRequest = new SearchRequest("tvs");
            /**
             * 聚合查询分析

             BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
             AggregationBuilders.dateHistogram("day_order");
             AggregationBuilders.dateHistogram("day_order");

             SearchSourceBuilder aggregation1 = searchSourceBuilder.query(boolQueryBuilder).aggregation(aggregation);
             */
            searchRequest.source(searchSourceBuilder);
            //打印DSL
            log.error(searchSourceBuilder.toString());
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

            /**
             * count 查询
             */
            CountRequest countRequest = new CountRequest("tvs");

            client.count(countRequest, RequestOptions.DEFAULT);
            //检索聚合结果
            Aggregations aggregations = searchResponse.getAggregations();
            DateHistogramAggregationBuilder dateHistogramAggregationBuilder = AggregationBuilders.dateHistogram("day_order");
            dateHistogramAggregationBuilder.field("time");


            getAllColors(searchResponse);

            Map<String, Map<String, Long>> groupMap = new HashMap<>();

            //注意,这里将发生异常ClassCastException
            //Range range = aggregations.get("group_by_color");
            //解决方法:
            Map<String, Aggregation> aggregationMap = aggregations.getAsMap();
            Terms companyAggregation = (Terms) aggregationMap.get("group_by_color");


        } catch (IOException e) {

            e.printStackTrace();
        }


    }

    private List<String> getAllColors(SearchResponse searchResponse) {

        List<String> colors = new ArrayList<>();
        //避免类型转换异常
        Terms terms1 = searchResponse.getAggregations().get("group_by_color");
        for (Terms.Bucket bucket1 : terms1.getBuckets()) {
            String key = bucket1.getKey().toString();// Keys
            String keyAsString = bucket1.getKeyAsString(); // Key as String
            long docCount = bucket1.getDocCount();         // Doc count
            Aggregations aggregations1 = bucket1.getAggregations();
            Map<String, Aggregation> asMap = aggregations1.getAsMap();
            Avg avgPrice = (Avg) asMap.get("avg_price");
            Terms groupByBrand = (Terms) asMap.get("group_by_brand");
            double value = avgPrice.getValue();
            log.error("key [{}], date [{}], doc_count [{}],avg_price [{}]", keyAsString, key, docCount, value);
            for (Terms.Bucket groupByBrands : groupByBrand.getBuckets()) {

                String keyBrand = groupByBrands.getKey().toString();
                String doc_count = String.valueOf(groupByBrands.getDocCount());

                log.error("keyBrand :[{}],doc_count :[{}]", keyBrand, doc_count);

            }


        }

        return colors;
    }

    ActionListener<SearchResponse> listener = new ActionListener<SearchResponse>() {
        @Override
        public void onResponse(SearchResponse searchResponse) {

        }

        @Override
        public void onFailure(Exception e) {

        }
    };


}
