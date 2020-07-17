package com.j1.repository;

import com.j1.pojo.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Repository;

/**
 * Created by wangchuanfu on 20/7/14.
 */
@EnableElasticsearchRepositories(basePackages = "com.j1.*")
public interface ItemRepository extends ElasticsearchRepository<Item,Long> {

}
