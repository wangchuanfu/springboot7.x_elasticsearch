package com.j1.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by wangchuanfu on 20/7/15.
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
//动态的获取索引名称,类型
public class EsAttribute {

    @Value("${es.indexname}")
    private String indexName;

    @Value("${es.indextype}")
    private String indexType;

    @Value("${es.suggestindexName}")
    private String suggestIndexName;

}
