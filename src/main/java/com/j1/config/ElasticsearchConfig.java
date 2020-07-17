package com.j1.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;

/**
 * Created by wangchuanfu on 20/7/14.
 */
@Configuration
public class ElasticsearchConfig {

    @Bean
    public ElasticsearchRestTemplate elasticsearchTemplate(RestHighLevelClient restHighLevelClient, ElasticsearchConverter converter) {
        return new ElasticsearchRestTemplate(restHighLevelClient, converter);
    }

}
