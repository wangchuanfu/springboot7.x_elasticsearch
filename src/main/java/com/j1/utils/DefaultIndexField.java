package com.j1.utils;

import com.j1.type.ProductFieldEnum;

/**
 * Created by wangchuanfu on 20/7/17.
 */
public interface DefaultIndexField {

    /**
     * 存储，并且索引的String类型字段
     */
    public static final String[] firstAnalyzeFields = {ProductFieldEnum.productCommonName.name(), // 商品通用名
            ProductFieldEnum.productName1.name(),// 产品名称
            ProductFieldEnum.productName.name(),// 产品名称
            ProductFieldEnum.productChnNo.name(),// 拼音码
            ProductFieldEnum.proCatalogName.name(),// 分类
            ProductFieldEnum.cataLogListName.name(),// 分类
            ProductFieldEnum.productBrandName.name(),// 品牌
            ProductFieldEnum.productBrandName1.name(),// 品牌
            ProductFieldEnum.productKeyword.name()};

    /**
     * 单个字段的boost，会乘以查询结果的score影响单条记录的排名
     */
    public static final Float[] firstAnalyzeFieldsBoost = {4.0f,// 商品通用名
            4.0f,// 产品名称
            4.0f,// 产品名称
            1.0f,// 拼音码
            4.0f,// 分类
            4.0f,// 分类名字列表
            4.0f,// 品牌
            4.0f, // 品牌
            4.0f // 品牌

    };

    /**
     * 存储，并且索引的String类型字段
     */
    public static final String[] analyzeFields = {
            // ProductFieldEnum.productName.name(),//产品名称
            // ProductFieldEnum.productChnNo.name(),//拼音码
            ProductFieldEnum.drugTreatment.name(),// 主治功能
            ProductFieldEnum.productKeyword.name() // 关键字
            // ,ProductFieldEnum.productBrandName.name()//品牌
    };

    /**
     * 单个字段的boost，会乘以查询结果的score影响单条记录的排名
     */
    public static final Float[] analyzeFieldsBoost = {
            // 4.0f,//产品名称
            // 1.0f,//拼音码
            1.0f,// 主治功能
            1.0f // 关键字
            // ,4.0f//品牌
    };

    /**
     * 用以排序的Double类型字段
     */
    public static final String[] sortDoubleFields = {ProductFieldEnum.saleAmount.name(),// 总销量
            ProductFieldEnum.ecPrice.name(),// 售价
            ProductFieldEnum.availableStock.name(),// 可用库存
            ProductFieldEnum.productOrder.name()};

    /**
     * 仅存储，不索引的String类型字段
     */
    public static final String[] storeFields = {ProductFieldEnum.proCatalogId.name(),// 分类ID
            ProductFieldEnum.proCatalogName.name(),// 分类名称
            ProductFieldEnum.promotePhrase.name(),// 促销信息
            ProductFieldEnum.proImageUrl.name(),// 图片路径
            ProductFieldEnum.tagIconUrl.name(),// 图标路径
            ProductFieldEnum.drugPrescriptionType.name(),// 商品类型
            ProductFieldEnum.productLeastOrder.name(),// 最小订购量
            ProductFieldEnum.orderLimitAmount.name(),// 最大订购量
            ProductFieldEnum.productId.name(), // 产品ID
            ProductFieldEnum.goodsId.name() // 商品ID
    };

    /**
     * 仅存储，不索引的Double类型字段
     */
    public static final String[] storeDoubleFields = {ProductFieldEnum.marketPrice.name() // 市场价
    };

    // 索引标识符，记录更新日期
    public static final String MODIFIED = "MODIFIED";

    public static final String KEYWORD = "keywordScore";

    // ------------------------ 搜索改版 2016-08-02 by tianqi *******start****** /
    /**
     * 存储，并且索引的String类型字段
     */
    public static final String[] secondAnalyzeFields = {ProductFieldEnum.productCommonName.name(), // 商品通用名
            // ProductFieldEnum.productName1.name(), //产品名称
            ProductFieldEnum.productName.name(),// 产品名称
            ProductFieldEnum.productChnNo.name(),// 拼音码
            ProductFieldEnum.proCatalogName.name(),// 分类
            // ProductFieldEnum.cataLogListName.name(),//分类
            ProductFieldEnum.productBrandName.name(), // 品牌
            ProductFieldEnum.productBrandName1.name(), // 品牌
            ProductFieldEnum.productKeyword.name() // 关键字

    };
    /**
     * 单个字段的boost，会乘以查询结果的score影响单条记录的排名
     */
    public static final Float[] secondAnalyzeFieldsBoost = {4.0f,// 商品通用名
            1.0f,// 商品名称
            1.0f,// 拼音码
            4.0f,// 品牌
            4.0f,// 品牌
            4.0f,// 品牌
            4.0f // 关键字

    };

    /**
     * 搜索改版 2016-08-02 by tianqi 存储，并且索引的String类型字段
     */
    public static final String[] thirdAnalyzeFields = {ProductFieldEnum.productCommonName.name(), // 商品通用名
            ProductFieldEnum.productName1.name(), // 产品名称
            ProductFieldEnum.productName.name(),// 产品名称
            ProductFieldEnum.productChnNo.name(),// 拼音码
            // ProductFieldEnum.proCatalogName.name(),//分类
            ProductFieldEnum.cataLogListName.name(),// 分类
            ProductFieldEnum.productKeyword.name(), // 关键字
            ProductFieldEnum.drugTreatment.name() // 主治功能
    };

    /**
     * 单个字段的boost，会乘以查询结果的score影响单条记录的排名
     */
    public static final Float[] thirdAnalyzeFieldsBoost = {4.0f,// 商品通用名
            4.0f,// 产品名称
            4.0f,// 产品名称
            1.0f,// 拼音码
            4.0f,// 品牌
            4.0f,// 主治功能
            4.0f // 关键字

    };

    // ---------------------------------搜索改版 2016-08-02 by tianqi
    // *******end****** /
}
