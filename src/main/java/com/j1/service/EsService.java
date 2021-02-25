package com.j1.service;

import com.j1.common.base.FacetsResult;
import com.j1.common.base.Page;
import com.j1.common.base.Pageable;
import org.apache.lucene.queries.mlt.MoreLikeThis;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.SortBuilder;

import java.util.List;
import java.util.Map;

/**
 * Created by wangchuanfu on 20/7/20.
 */
public interface EsService<T> {
    /**
     * 查找
     *
     * @param query
     * @param page
     * @return
     */
    public Page<T> search(QueryBuilder query, Pageable page);
    /**
     * 查找
     * @param query
     * @param filter
     * @param page
     * @return
     */
    // public Page<T> search(QueryBuilder query, FilterBuilder filter, Pageable page);

    /**
     * 查找
     *
     * @param query
     * @param page
     * @param highlightFields
     * @return
     */
    public Page<T> search(QueryBuilder query, Pageable page, String... highlightFields);
    /**
     * 查找
     * @param query
     * @param filter
     * @param page
     * @param highlightFields
     * @return
     */
    // public Page<T> search(QueryBuilder query, FilterBuilder filter, Pageable page, String...highlightFields);

    /**
     * 查找
     *
     * @param query
     * @param size
     * @param sorts
     * @return
     */
    public List<T> search(QueryBuilder query, int size, List<SortBuilder> sorts);

    /**
     * 查找
     *
     * @param query
     * @param start
     * @param size
     * @return
     */
    public List<T> search(QueryBuilder query, int start, int size);
    /**
     * 查找
     * @param query
     * @param filter
     * @param start
     * @param size
     * @return
     */
    //public List<T> search(QueryBuilder query, FilterBuilder filter, int start, int size);

    /**
     * 查找
     *
     * @param query
     * @param start
     * @param size
     * @param sorts
     * @return
     */
    public List<T> search(QueryBuilder query, int start, int size, List<SortBuilder> sorts);
    /**
     * 查找
     * @param query
     * @param filter
     * @param start
     * @param size
     * @param sorts
     * @return
     */
    // public List<Map<String, Object>> searchToMap(QueryBuilder query, FilterBuilder filter, int start, int size, List<SortBuilder> sorts);
    /**
     * 分组查找
     * @param query
     * @param filter
     * @param key
     * @param facets
     * @return
     */
    // public FacetsResult searchFacets(QueryBuilder query, FilterBuilder filter, String key, FacetBuilder facets);
    /**
     * 分组查找
     * @param query
     * @param filter
     * @param key
     * @param facets
     * @param start
     * @param size
     * @param sorts
     * @param highlightFields
     * @return
     */
    //public FacetsResult searchFacets(QueryBuilder query, FilterBuilder filter, String key,FacetBuilder facets, int start, int size, List<SortBuilder> sorts, String...highlightFields);
    /**
     * 仅分组查找
     * @param query
     * @param filter
     * @param key
     * @param facets
     * @return
     */
    // public FacetsResult searchOnlyFacets(QueryBuilder query, FilterBuilder filter, String key, FacetBuilder facets);
    /**
     * 分查找并返回结果Page
     * @param query
     * @param filter
     * @param key
     * @param facets
     * @param page
     * @param highlightFields
     * @return
     */
    // public FacetsResult searchCustomPageAndFacets(QueryBuilder query, FilterBuilder filter, String key, FacetBuilder facets, Pageable page, String... highlightFields);

    /**
     * 多次合并查找
     *
     * @param requests
     * @return
     */
    public List<List<T>> multiSearch(SearchRequestBuilder[] requests);

    /**
     * 统计总数
     *
     * @param query
     * @return
     */
    public long count(QueryBuilder query);

    /**
     * 统计总数
     *
     * @return
     */
    public long count();

    /**
     * 根据id获得单挑索引的实体
     *
     * @param id
     * @return
     */
    public T get(String id);

    /**
     * 根据多个id获得多个索引实体的Map
     *
     * @param ids
     * @return
     */
    public Map<String, T> multiGetToMap(String[] ids);

    /**
     * 根据多个id获得多个索引实体的List
     *
     * @param ids
     * @return
     */
    public List<T> multiGetToList(String[] ids);

    /**
     * 基于内容的推荐查询
     *
     * @param request
     * @return
     */
    public List<T> moreLikeThisQuery(MoreLikeThis request);

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    public DeleteResponse deleteById(String id);

    /**
     * 根据多个id删除
     *
     * @param ids
     * @return
     */
    public boolean deleteByIds(List<String> ids);

    /**
     * 对一个实体进行索引
     *
     * @param entity
     * @return
     */
    public boolean addByBean(T entity);

    /**
     * 对多个实体进行索引
     *
     * @param entity
     * @return
     */
    public boolean addByBeans(List<T> entity);

    /**
     * 获取索引库名
     *
     * @return
     */
    public String getIndexName();

    /**
     * 获取索引类型
     *
     * @return
     */
    public String getIndexType();
}
