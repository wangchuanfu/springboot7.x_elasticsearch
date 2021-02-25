package com.j1.xiaoxiang.repository;

import com.j1.pojo.vo.ShoesTrade;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ShoesTradeRepository extends ElasticsearchRepository<ShoesTrade, Long> {
}
