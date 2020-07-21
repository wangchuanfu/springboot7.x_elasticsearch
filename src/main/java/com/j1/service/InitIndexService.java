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
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
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
    private static Logger logger = LoggerFactory.getLogger(InitIndexService.class);

    @Resource
    IntoEsUtils intoEsUtils;
    @Resource
    @Qualifier("restHighLevelClient")
    public RestHighLevelClient client;
    @Resource
    EsAttribute esAttribute;

    public List<Map<String, Object>> search(String keyword, Integer pageNo, Integer pageSize) throws Exception {
        return querySearch(keyword, pageNo, pageSize);
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

    //此处方法可以抽出来,但是为了加强记忆,还是手写一遍
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
            //过滤
            BoolQueryBuilder postFilter = QueryBuilders.boolQuery();
            postFilter.mustNot(QueryBuilders.termQuery("ecPrice", -1));
            //显示高亮
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("productName");
            highlightBuilder.preTags("<span style='color:red'>");
            highlightBuilder.postTags("</span>");
            searchSourceBuilder.highlighter(highlightBuilder);
            searchSourceBuilder.query(boolQuery2);
            searchSourceBuilder.postFilter(postFilter);

            searchRequest.source(searchSourceBuilder);

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
            logger.error(searchSourceBuilder.toString());
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
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
