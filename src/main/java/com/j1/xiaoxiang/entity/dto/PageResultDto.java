package com.j1.xiaoxiang.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class PageResultDto<T> {
    /**
     * 目标页码
     */
    private int targetPage = 1;
    /**
     * 每页条数
     */
    private int pageSize = 20;
    /**
     * 总页数
     */
    private int pageCount;
    /**
     * 总记录数
     */
    private int totalRecord;
    /**
     * 目标页码结果集
     */
    private List<T> itemList;

    /**
     * 商品品牌聚合结果
     */
    private Set<String> brandAgg;
    /**
     * 商品类目聚合结果
     */
    private Set<String> categoryAgg;

    /**
     * 商品类目聚合结果
     */
    private Set<String> platformAgg;

    /**
     * 商品类目聚合结果
     */
    private Set<String> serisNameAgg;


    /**
     * 商品类目聚合结果
     */
    private Set<String> suitCrowdAgg;


    /**
     * 商品类目聚合结果
     */
    private List<String> measuresAgg;

    public PageResultDto(int targetPage, int pageSize, int totalRecord, List<T> itemList, Set<String> brandAgg,
                         Set<String> categoryAgg, Set<String> platformAgg,
                         Set<String> serisNameAgg, Set<String> suitCrowdAgg, List<String> measuresAgg) {
        this.targetPage = targetPage;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;
        this.itemList = itemList;
        this.brandAgg = brandAgg;
        this.categoryAgg = categoryAgg;
        this.platformAgg = platformAgg;
        this.serisNameAgg = serisNameAgg;
        this.suitCrowdAgg = suitCrowdAgg;
        this.measuresAgg = measuresAgg;
        buildPageCount();
    }

    public PageResultDto(int targetPage, int pageSize, int totalRecord, List<T> itemList, Set<String> brandAgg, Set<String> categoryAgg, Set<String> platformAgg) {
        this.targetPage = targetPage;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;
        this.itemList = itemList;
        this.brandAgg = brandAgg;
        this.categoryAgg = categoryAgg;
        this.platformAgg = platformAgg;
        buildPageCount();
    }

    public PageResultDto() {
    }

    public PageResultDto(int targetPage, int pageSize, int totalRecord, List<T> itemList) {
        this.targetPage = targetPage;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;
        this.itemList = itemList;
        buildPageCount();
    }

    private void buildPageCount() {
        if (totalRecord % pageSize == 0) {
            pageCount = totalRecord / pageSize;
        } else {
            pageCount = (totalRecord / pageSize) + 1;
        }
    }
}
