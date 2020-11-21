package com.j1.xiaoxiang.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResultDto<T> {

    private int targetPage;//目标页
    private int pageSize=28;//每页条数.默认28条
    private int pageCount;//页数
    private int totalRecord;//总条数
    private List<T> dataList;//结果集
    private Set<T> brandAgg;
    private Set<String> categoryAgg;//商品类目聚合结果
    private Set<String> platformAgg;//商品类目聚合结果
    private Set<String> serisNameAgg;
    private Set<String> suitCrowdAgg;
}
