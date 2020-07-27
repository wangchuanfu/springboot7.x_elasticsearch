package com.j1.service.impl;


import com.j1.common.base.PageRequest;
import com.j1.common.base.Pageable;
import com.j1.common.base.Sort;
import com.j1.common.utils.BoolQueryBuilders;
import com.j1.pojo.EsAttribute;
import com.j1.service.InitIndexService;
import com.j1.service.IntoEsUtils;
import com.j1.service.SearchProductService;

import com.j1.utils.DefaultIndexField;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.DocValueFormat;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by wangchuanfu on 20/7/20.
 */
@Service
@Slf4j
public class SearchProductServiceImpl implements SearchProductService {
   @Resource
   InitIndexService initIndexService;
    @Resource
    SearchProductService searchProductService;

    @Resource
    IntoEsUtils intoEsUtils;
    @Resource
    @Qualifier("restHighLevelClient")
    public RestHighLevelClient client;
    @Resource
    EsAttribute esAttribute;
    @Override
    public List<Map<String, Object>> querySearch(String keyword, Integer pageNo, Integer pageSize) {

        try {
            initIndexService.querySearch(keyword,pageNo,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     *
     * @param keyword
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public String querySearchGoods(String keyword, Integer pageNo, Integer pageSize) {


        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "_score");
        Sort sort = new Sort("98");
        sort.add(order);
        PageRequest pageRequest = new PageRequest(pageNo, pageSize, sort);
        BoolQueryBuilder boolQuery = getBoolQueryBuilder(keyword);
        //getQueryByBoost(keyword, boolQuery);
        BoolQueryBuilder boolQuery2 = QueryBuilders.boolQuery().should(boolQuery);
        return searchGoodsList(boolQuery2, pageRequest);
    }
    private String searchGoodsList(BoolQueryBuilder boolQuery2, Pageable page) {
        List<Map<String, Object>> list = new ArrayList<>();
        try {

            int start = page.getOffset();
            int size = page.getPageSize();
            SearchRequest searchRequest = new SearchRequest(esAttribute.getIndexName());
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

            BoolQueryBuilder postFilter = QueryBuilders.boolQuery();
            postFilter.mustNot(QueryBuilders.termQuery("ecPrice", -1));
            //范围查询
            QueryBuilder postFilter2 = QueryBuilders.rangeQuery("ecPrice").gte(30).lte(50);
            searchSourceBuilder.postFilter(postFilter2);
            //显示高亮
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("productName");
            highlightBuilder.preTags("<span style='color:red'>");
            highlightBuilder.postTags("</span>");
            searchSourceBuilder.highlighter(highlightBuilder);
            searchSourceBuilder.query(boolQuery2);
            searchSourceBuilder.postFilter(postFilter);
            searchSourceBuilder.postFilter(postFilter2);
            //聚合分组
            searchSourceBuilder.aggregation(AggregationBuilders.avg("avg_price").field("ecPrice"));//最后的聚合求平均数

            //dateHistogram 分组
            DateHistogramAggregationBuilder dateHistogramAggregationBuilder =
                    AggregationBuilders.dateHistogram("agg").field("dateOfBirth").dateHistogramInterval(DateHistogramInterval.YEAR);

            searchSourceBuilder.aggregation(dateHistogramAggregationBuilder);


            searchSourceBuilder.from(start);
            searchSourceBuilder.size(size);

            List<SortBuilder> sorts = parsePageSort(page.getSort());
            // 排序

            if (sorts != null) {
                for (SortBuilder sort : sorts) {
                    searchSourceBuilder.sort(sort);
                }
            }

            //另一种排序的写法
            FieldSortBuilder ecPrice = new FieldSortBuilder("ecPrice").order(SortOrder.ASC);
            //  FieldSortBuilder _score = new FieldSortBuilder("_score").order(SortOrder.DESC);
            FieldSortBuilder saleTime = new FieldSortBuilder("saleTime").order(SortOrder.DESC);
            //searchSourceBuilder.sort(_score);
            searchSourceBuilder.sort(ecPrice); //根据field DESC 排序
            searchSourceBuilder.sort(saleTime);
            log.error(searchSourceBuilder.toString());
           // logger.error(searchSourceBuilder.toString());
            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);


            Aggregation aggregation = searchResponse.getAggregations().get("avg_price");

            Histogram aggs = searchResponse.getAggregations().get("agg");

            List<? extends Histogram.Bucket> buckets = aggs.getBuckets();
            for (Histogram.Bucket agg: buckets) {

                DocValueFormat.DateTime key = (DocValueFormat.DateTime)agg.getKey();
                String keyAsString = agg.getKeyAsString(); // Key as String
                long docCount = agg.getDocCount(); // Doc count
            }

            //循环遍历
            SearchHit[] searchHits = searchResponse.getHits().getHits();
            SearchHits hits = searchResponse.getHits();
            //查询出来的总数据
            long total = hits.getTotalHits().value;
            log.error( Long.toString(total));
            for (SearchHit searchHit : searchHits) {
                Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();//查询的原来的结果
                Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
                HighlightField title = highlightFields.get("productName");
                if (title != null) {
                    //解析高亮字段,将之前查出来的没高亮的字段 替换为高亮字段
                    Text[] fragments = title.fragments();
                    StringBuilder newTitle = new StringBuilder();
                    for (Text fragment : fragments) {
                        newTitle.append(fragment.string());
                    }
                    sourceAsMap.put("productName", newTitle);
                }
                list.add(sourceAsMap);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return list.toString();
    }


    //构建boolQuery
    private BoolQueryBuilder getBoolQueryBuilder(String keyword) {
        //构造查询条件
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        for (int i = 0; i < DefaultIndexField.secondAnalyzeFields.length ; i++) {
            String tfieldName = DefaultIndexField.secondAnalyzeFields[i];
            float tboost = DefaultIndexField.secondAnalyzeFieldsBoost[i] != null ? DefaultIndexField.secondAnalyzeFieldsBoost[i] : 1.0f;

            BoolQueryBuilders.shouldFieldQueryAND(boolQuery, tfieldName, keyword, tboost);
        }
        BoolQueryBuilders.shouldFieldQueryAND(boolQuery, "productName", keyword);
        return boolQuery;
    }

    /**
     * 构建排序对象列表
     *
     * @param sort
     * @return
     */
    protected List<SortBuilder> parsePageSort(Sort sort) {
        List<SortBuilder> sorts = null;
        if (sort != null) {
            sorts = new ArrayList<SortBuilder>();
            Iterator<Sort.Order> orders = sort.iterator();
            while (orders.hasNext()) {
                Sort.Order order = orders.next();
                SortBuilder esSort = buildSort(order.getProperty(), order.getDirection().toString());
                sorts.add(esSort);
            }
        }
        return sorts;
    }

    /**
     * 组装排序对象，若非asc，就使用desc
     *
     * @param field排序字段
     * @param order排序方式asc ,desc
     * @return
     */
    protected static SortBuilder buildSort(String field, String order) {
        SortBuilder sort = SortBuilders.fieldSort(field);
        if ("asc".equalsIgnoreCase(order)) {
            sort.order(SortOrder.ASC);
        } else {
            sort.order(SortOrder.DESC);
        }
        return sort;
    }

    private BoolQueryBuilder getQueryByBoost(String keyword, BoolQueryBuilder boolQuery) {
        BoolQueryBuilder saleAmountBoolQuery = QueryBuilders.boolQuery();

        /**
         * 人工维护的关键字分为6个权重级别 分别是 50 40 30 20 10 5
         */

        /**
         * 销量权重设置级别 一共有2个级别 (50-,100)
         */
        QueryBuilder saleAmountRangeQuery = QueryBuilders.rangeQuery("saleScore").gt(50).boost(200);
        QueryBuilder saleAmountRangeQuery2 = QueryBuilders.rangeQuery("saleScore").lt(50).boost(1);

        saleAmountBoolQuery.should(saleAmountRangeQuery);
        saleAmountBoolQuery.should(saleAmountRangeQuery2);

        boolQuery.must(saleAmountBoolQuery);
        return boolQuery;
    }
}