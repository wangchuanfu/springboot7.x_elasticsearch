package com.j1.service.impl;

import com.j1.common.utils.BoolQueryBuilders;
import com.j1.dao.MemberMapper;
import com.j1.pojo.EsAttribute;
import com.j1.pojo.Member;
import com.j1.service.InitIndexService;
import com.j1.service.IntoEsUtils;
import com.j1.service.MemberService;
import com.j1.service.SearchProductService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wangchuanfu on 20/8/13.
 */
@Service
@Slf4j
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberMapper memberMapper;

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
    private static final String orderIndex = "member_info_index";

    @Override
    public List<Member> queryMemberPage(Member member) {
        return memberMapper.queryMemberPage(member);
    }

    @Override
    public String searchMemberInfo(String keyword) {
        String loginName = keyword;
        BoolQueryBuilder boolQuery = getBoolQueryBuilder(loginName);

        //getQueryByBoost(keyword, boolQuery);
        BoolQueryBuilder boolQuery2 = QueryBuilders.boolQuery().should(boolQuery);
        return searchMemberList(boolQuery2);


    }

    private String searchMemberList(BoolQueryBuilder boolQuery2) {

        List<Map<String, Object>> list = new ArrayList<>();
        try {
            SearchRequest searchRequest = new SearchRequest(orderIndex);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

            BoolQueryBuilder postFilter = QueryBuilders.boolQuery();
            // postFilter.mustNot(QueryBuilders.termQuery("ecPrice", -1));
            //范围查询
            //  QueryBuilder postFilter2 = QueryBuilders.rangeQuery("ecPrice").gte(30).lte(50);
            //searchSourceBuilder.postFilter(postFilter2);
            //显示高亮
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("loginName");
            highlightBuilder.preTags("<span style='color:red'>");
            highlightBuilder.postTags("</span>");
            searchSourceBuilder.highlighter(highlightBuilder);
            searchSourceBuilder.query(boolQuery2);
            searchSourceBuilder.postFilter(postFilter);


            log.error(searchSourceBuilder.toString());
            // logger.error(searchSourceBuilder.toString());
            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse = null;
            try {
                searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            } catch (IOException e1) {
                e1.printStackTrace();
            }


            //循环遍历
            SearchHit[] searchHits = searchResponse.getHits().getHits();
            SearchHits hits = searchResponse.getHits();
            //查询出来的总数据
            long total = hits.getTotalHits().value;
            log.error(Long.toString(total));
            for (SearchHit searchHit : searchHits) {
                Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();//查询的原来的结果
                Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
                HighlightField title = highlightFields.get("loginName");
                if (title != null) {
                    //解析高亮字段,将之前查出来的没高亮的字段 替换为高亮字段
                    Text[] fragments = title.fragments();
                    StringBuilder newTitle = new StringBuilder();
                    for (Text fragment : fragments) {
                        newTitle.append(fragment.string());
                    }
                    sourceAsMap.put("loginName", newTitle);
                }
                list.add(sourceAsMap);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return list.toString();
    }


    private BoolQueryBuilder getBoolQueryBuilder(String loginName) {
        //构造查询条件
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        /*


        for (int i = 0; i < DefaultIndexField.searchParams.length ; i++) {
            String tfieldName = DefaultIndexField.searchParams[i];
           // float tboost = DefaultIndexField.secondAnalyzeFieldsBoost[i] != null ? DefaultIndexField.secondAnalyzeFieldsBoost[i] : 1.0f;

            BoolQueryBuilders.shouldFieldQueryAND(boolQuery, tfieldName, loginName, 4);
        }*/
        BoolQueryBuilders.shouldFieldQueryAND(boolQuery, "loginName", loginName);
        return boolQuery;
    }
}
