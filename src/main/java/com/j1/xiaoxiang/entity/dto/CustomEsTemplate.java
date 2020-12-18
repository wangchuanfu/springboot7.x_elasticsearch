package com.j1.xiaoxiang.entity.dto;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchHitsIterator;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.data.util.CloseableIterator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomEsTemplate implements ElasticsearchOperations, ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    @Override
    public IndexOperations indexOps(Class<?> aClass) {
        return null;
    }

    @Override
    public IndexOperations indexOps(IndexCoordinates indexCoordinates) {
        return null;
    }

    @Override
    public ElasticsearchConverter getElasticsearchConverter() {
        return null;
    }

    @Override
    public IndexCoordinates getIndexCoordinatesFor(Class<?> aClass) {
        return null;
    }

    @Override
    public <T> T save(T t) {
        return null;
    }

    @Override
    public <T> T save(T t, IndexCoordinates indexCoordinates) {
        return null;
    }

    @Override
    public <T> Iterable<T> save(Iterable<T> iterable) {
        return null;
    }

    @Override
    public <T> Iterable<T> save(Iterable<T> iterable, IndexCoordinates indexCoordinates) {
        return null;
    }

    @Override
    public <T> Iterable<T> save(T... ts) {
        return null;
    }

    @Override
    public String index(IndexQuery indexQuery, IndexCoordinates indexCoordinates) {
        return null;
    }

    @Override
    public <T> T get(String s, Class<T> aClass) {
        return null;
    }

    @Override
    public <T> T get(String s, Class<T> aClass, IndexCoordinates indexCoordinates) {
        return null;
    }

    @Override
    public <T> List<T> multiGet(Query query, Class<T> aClass, IndexCoordinates indexCoordinates) {
        return null;
    }

    @Override
    public boolean exists(String s, Class<?> aClass) {
        return false;
    }

    @Override
    public boolean exists(String s, IndexCoordinates indexCoordinates) {
        return false;
    }

    @Override
    public List<String> bulkIndex(List<IndexQuery> list, BulkOptions bulkOptions, IndexCoordinates indexCoordinates) {
        return null;
    }

    @Override
    public void bulkUpdate(List<UpdateQuery> list, BulkOptions bulkOptions, IndexCoordinates indexCoordinates) {

    }

    @Override
    public String delete(String s, IndexCoordinates indexCoordinates) {
        return null;
    }

    @Override
    public String delete(String s, Class<?> aClass) {
        return null;
    }

    @Override
    public String delete(Object o) {
        return null;
    }

    @Override
    public String delete(Object o, IndexCoordinates indexCoordinates) {
        return null;
    }

    @Override
    public void delete(Query query, Class<?> aClass, IndexCoordinates indexCoordinates) {

    }

    @Override
    public UpdateResponse update(UpdateQuery updateQuery, IndexCoordinates indexCoordinates) {
        return null;
    }

    @Override
    public void delete(DeleteQuery deleteQuery, IndexCoordinates indexCoordinates) {

    }

    @Override
    public <T> T get(GetQuery getQuery, Class<T> aClass, IndexCoordinates indexCoordinates) {
        return null;
    }

    @Override
    public <T> T queryForObject(GetQuery getQuery, Class<T> aClass) {
        return null;
    }

    @Override
    public long count(Query query, Class<?> aClass) {
        return 0;
    }

    @Override
    public long count(Query query, Class<?> aClass, IndexCoordinates indexCoordinates) {
        return 0;
    }

    @Override
    public <T> CloseableIterator<T> stream(Query query, Class<T> aClass, IndexCoordinates indexCoordinates) {
        return null;
    }

    @Override
    public SearchResponse suggest(SuggestBuilder suggestBuilder, IndexCoordinates indexCoordinates) {
        return null;
    }

    @Override
    public <T> List<SearchHits<T>> multiSearch(List<? extends Query> list, Class<T> aClass, IndexCoordinates indexCoordinates) {
        return null;
    }

    @Override
    public List<SearchHits<?>> multiSearch(List<? extends Query> list, List<Class<?>> list1, IndexCoordinates indexCoordinates) {
        return null;
    }

    @Override
    public <T> SearchHits<T> search(Query query, Class<T> aClass) {
        return null;
    }

    @Override
    public <T> SearchHits<T> search(Query query, Class<T> aClass, IndexCoordinates indexCoordinates) {
        return null;
    }

    @Override
    public <T> SearchHits<T> search(MoreLikeThisQuery moreLikeThisQuery, Class<T> aClass) {
        return null;
    }

    @Override
    public <T> SearchHits<T> search(MoreLikeThisQuery moreLikeThisQuery, Class<T> aClass, IndexCoordinates indexCoordinates) {
        return null;
    }

    @Override
    public <T> SearchHitsIterator<T> searchForStream(Query query, Class<T> aClass) {
        return null;
    }

    @Override
    public <T> SearchHitsIterator<T> searchForStream(Query query, Class<T> aClass, IndexCoordinates indexCoordinates) {
        return null;
    }

}
