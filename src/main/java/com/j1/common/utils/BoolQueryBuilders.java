package com.j1.common.utils;

import org.elasticsearch.index.query.*;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.isNotBlank;

/**
 * Created by wangchuanfu on 20/7/21.
 */
public class BoolQueryBuilders {

    //=========================must========================

    public static BoolQueryBuilder mustRangeQueryBuild(
            BoolQueryBuilder mustQuery, String field, int from, int to)
    {
        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery(field);
        if (from > to)
        {
            return mustQuery;
        }
        else if (from == 0 && to == 0)
        {
            return mustQuery;
        }
        rangeQuery.from(from).includeLower(true);
        if (to != 0)
        {
            rangeQuery.to(to).includeUpper(true);
        }
        mustQuery.must(rangeQuery);
        return mustQuery;
    }

    public static BoolQueryBuilder mustGreaterThanQueryBuild(
            BoolQueryBuilder mustQuery, String field, int from)
    {
        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery(field);
        rangeQuery.from(from).includeLower(false);
        mustQuery.must(rangeQuery);
        return mustQuery;
    }

    public static BoolQueryBuilder mustLessThanQueryBuild(
            BoolQueryBuilder mustQuery, String field, int to)
    {
        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery(field);
        rangeQuery.to(to).includeLower(false);
        mustQuery.must(rangeQuery);
        return mustQuery;
    }

    /**
     *
     * @param mustQuery
     * @param field
     * @param query
     */
    public static BoolQueryBuilder mustMatchQueryAND(
            BoolQueryBuilder mustQuery, String field, String query)
    {
        BoolQueryBuilders.mustMatchQueryAND(mustQuery, field, query, 1.0f);
        return mustQuery;
    }

    public static BoolQueryBuilder mustMatchQueryAND(
            BoolQueryBuilder mustQuery, String field, String query, float boost)
    {
        if (isNotBlank(query))
        {
            QueryBuilder fieldQuery = QueryBuilders.matchQuery(field,
                    query).operator(Operator.AND).boost(boost);
            mustQuery.must(fieldQuery);
        }
        return mustQuery;
    }

    /**
     * @param mustQuery
     * @param field
     * @param offShelf
     */
    public static BoolQueryBuilder mustMatchQueryOR(
            BoolQueryBuilder mustQuery, String field, String query)
    {
        BoolQueryBuilders.mustMatchQueryOR(mustQuery, field, query, 1.0f);
        return mustQuery;
    }

    public static BoolQueryBuilder mustMatchQueryOR(
            BoolQueryBuilder mustQuery, String field, String query, float boost)
    {
        if (isNotBlank(query))
        {
            QueryBuilder fieldQuery = QueryBuilders.matchQuery(field,
                    query).operator(Operator.OR).boost(boost);
            mustQuery.must(fieldQuery);
        }
        return mustQuery;
    }


    /**
     * @param mustQuery
     * @param string
     * @param categoryId
     */
    public static BoolQueryBuilder mustTermQueryBuild(
            BoolQueryBuilder mustQuery, String field, String query, float boost)
    {
        TermQueryBuilder termQuery = QueryBuilders.termQuery(field, query).boost(boost);
        mustQuery.must(termQuery);
        return mustQuery;
    }

    public static BoolQueryBuilder mustTermQueryBuild(
            BoolQueryBuilder shouldQuery, String field, String query)
    {
        BoolQueryBuilders.mustTermQueryBuild(shouldQuery, field, query, 1.0f);
        return shouldQuery;
    }

    /**
     * @param mustQuery
     * @param string
     * @param categoryId
     */
    public static BoolQueryBuilder mustNotTermQueryBuild(
            BoolQueryBuilder mustQuery, String field, String query)
    {
        TermQueryBuilder termQuery = QueryBuilders.termQuery(field, query);
        mustQuery.mustNot(termQuery);
        return mustQuery;
    }



    //==================================should========================

    /**
     *
     * @param mustQuery
     * @param field
     * @param query
     */
    public static BoolQueryBuilder shouldFieldQueryAND(
            BoolQueryBuilder shouldQuery, String field, String query)
    {
        BoolQueryBuilders.shouldFieldQueryAND(shouldQuery, field, query, 1.0f);
        return shouldQuery;
    }

    public static BoolQueryBuilder shouldFieldQueryAND(
            BoolQueryBuilder shouldQuery, String field, String query, float boost)
    {
        if (isNotBlank(query))
        {
            QueryBuilder fieldQuery = QueryBuilders.matchQuery(field,
                    query).operator(Operator.AND).boost(boost);
            shouldQuery.should(fieldQuery);
        }
        return shouldQuery;
    }

    public static BoolQueryBuilder shouldFieldQueryOR(
            BoolQueryBuilder shouldQuery, String field, String query)
    {
        BoolQueryBuilders.shouldFieldQueryOR(shouldQuery, field, query, 1.0f);
        return shouldQuery;
    }

    /**
     *
     * @param mustQuery
     * @param field
     * @param query
     */
    public static BoolQueryBuilder shouldFieldQueryOR(
            BoolQueryBuilder shouldQuery, String field, String query, float boost)
    {
        if (isNotBlank(query))
        {
            QueryBuilder fieldQuery = QueryBuilders.matchQuery(field,
                    query).operator(Operator.OR).boost(boost).minimumShouldMatch("50%");
            shouldQuery.should(fieldQuery);
        }
        return shouldQuery;
    }

    /**
     *
     * @param mustQuery
     * @param field
     * @param query
     */
    public static BoolQueryBuilder shouldTermQueryBuild(
            BoolQueryBuilder shouldQuery, String field, String query, float boost)
    {
        if (isNotBlank(query))
        {
            TermQueryBuilder fieldQuery = QueryBuilders.termQuery(field, query).boost(boost);
            shouldQuery.should(fieldQuery);
        }
        return shouldQuery;
    }

    public static BoolQueryBuilder shouldTermQueryBuild(
            BoolQueryBuilder shouldQuery, String field, String query)
    {
        BoolQueryBuilders.shouldTermQueryBuild(shouldQuery, field, query, 1.0f);
        return shouldQuery;
    }

    /**
     *
     * @param sortField
     * @param order
     * @return
     */
    public static List<SortBuilder> sortListBuild(String sortField,
                                                  SortOrder order)
    {
        List<SortBuilder> sorts = new ArrayList<SortBuilder>();
        SortBuilder sort = SortBuilders.fieldSort(sortField).order(order);
        sorts.add(sort);
        return sorts;
    }
}
