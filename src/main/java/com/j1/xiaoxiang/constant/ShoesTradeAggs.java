package com.j1.xiaoxiang.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 聚合
 */
public enum ShoesTradeAggs {

    /**
     * 品牌聚合
     */
    shoesTradeBrandAgg("brandAgg","brandName"),

    /**
     * 平台来源聚合的聚合名与聚合字段
     */
    shoesTradePlatformAgg("platformAgg", "thirdPlatformCode.keyword"),
    /**
     * 系列名称聚合的聚合名与聚合字段
     */
    shoesTradeserisNameAgg("serisAgg", "serisName"),
    /**
     * 适合人群聚合的聚合名与聚合字段
     */
    shoesTradesuitCrowdAgg("suitCrowdAgg", "suitCrowd"),
    /**
     * 尺寸聚合的聚合名与聚合字段
     */
    shoesTrademeasuresAgg("measuresAgg", "measures"),
    /**
     * 三级类目聚合的聚合名与聚合字段
     */
    shoesTradeCategoryAgg("categoryAgg", "productCategoryName");
    private  String aggName;
    private String fieldName;

    public String getAggName() {
        return aggName;
    }

    public void setAggName(String aggName) {
        this.aggName = aggName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    ShoesTradeAggs(String aggName, String fieldName) {
        this.aggName = aggName;
        this.fieldName = fieldName;
    }
}
