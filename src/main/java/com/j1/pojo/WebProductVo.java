package com.j1.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * Created by wangchuanfu on 20/7/14.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
// 搜索列表页面参数Vo spring-data-elasticsearch 注解,基于model创建index,mapping
//@Document(indexName = "hy_web_index",type = "webproductvo", shards = 5, replicas = 1)
@Document(indexName = "#{esAttribute.indexName}", type = "#{esAttribute.indexType}", shards = 5, replicas = 1)
public class WebProductVo {
        /**
         * IndexField标签内的属性是字段在索引库中的Mapping，一旦创建，不可更改
         */
    // ======================存储+索引+String类型===========================
    /**
     * 主治功能
     */

    // @Field(type = FieldType.Text, analyzer = "ik_max_word",store = true)
   // @IndexField(type = "string", index = "analyzed", index_analyzer = "index_ansj", search_analyzer = "query_ansj", store = "yes")
    @Field(type = FieldType.Text ,index =true, analyzer="ik_max_word",    searchAnalyzer = "ik_max_word"  ,store = true)//动态获取index,type
    private String drugTreatment;
    /**
     * 关键字
     */
    @Field(type = FieldType.Text ,index =true, analyzer="ik_max_word",    searchAnalyzer = "ik_max_word"  ,store = true)
    private String productKeyword;

    /**
     * 商品通用名
     */
    @Field(type = FieldType.Text ,index =true, analyzer="ik_max_word",    searchAnalyzer = "ik_max_word"  ,store = true)
    private String productCommonName;

    /**
     * 品牌
     */
    @Field(type = FieldType.Text ,index =true, analyzer="ik_max_word",    searchAnalyzer = "ik_max_word"  ,store = true)
    private String productBrandName;

    /**
     * 品牌
     */
    @Field(type = FieldType.Text ,index =true, analyzer="ik_max_word",    searchAnalyzer = "ik_max_word"  ,store = true)
    private String productBrandName1;

    /**
     * 产品名称
     */
    @Field(type = FieldType.Text ,index =true, analyzer="ik_max_word",    searchAnalyzer = "ik_max_word"  ,store = true)
    private String productName;

    /**
     * 产品名称
     */
    @Field(type = FieldType.Text ,index =true, analyzer="ik_max_word",    searchAnalyzer = "ik_max_word"  ,store = true)
    private String productName1;

    /**
     * 生产厂家
     */
    @Field(type = FieldType.Text ,index =true, analyzer="standard",    searchAnalyzer = "standard"  ,store = true)
    private String producer;

    /**
     * 拼音码
     */
    @Field(type = FieldType.Text ,index =true, analyzer="ik_max_word",    searchAnalyzer = "ik_max_word"  ,store = true)
    private String productChnNo;
    /**
     * 分类筛选属性
     */
    @Field(type = FieldType.Text ,index =true, analyzer="standard",    searchAnalyzer = "standard"  ,store = true)
    private String attrs;
    /**
     * 分类名称
     */
    @Field(type = FieldType.Text ,index =true, analyzer="ik_max_word",    searchAnalyzer = "ik_max_word"  ,store = true)
    private String proCatalogName;
    /**
     * 分类关联目录ID
     */
    @Field(type = FieldType.Text ,index =true ,store = true)
    private String cataLogListId;
    /**
     * 分类关联目录名字
     */
    @Field(type = FieldType.Text ,index =true, analyzer="ik_max_word",    searchAnalyzer = "ik_max_word"  ,store = true)
    private String cataLogListName;

    /**
     * 分类目录3级Id
     */
    @Field(type = FieldType.Keyword ,index =true,store = true)
    private String cataLogPathId;
// ======================存储+不分词索引+String类型======================
    /**
     * 品牌Id
     */
    @Field(type = FieldType.Keyword ,index =true, store = true)
    private String productBrandId;

    /**
     * 分类ID
     */
    @Field(type = FieldType.Keyword ,index =true,store = true)
    private String proCatalogId;


    /**
     * 是否是促销产品
     */
    @Field(type = FieldType.Keyword ,index =true,store = true)
    private String discount;

    /**
     * 上架时间
     */

    @Field(type = FieldType.Keyword ,index =false ,store = true)

    private Date saleTime;

    @Field(type = FieldType.Date,format = DateFormat.custom,pattern ="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;





    // ======================存储+不分词索引+Double类型===========================
    /**
     * 销量评分
     */
    @Field(type = FieldType.Double ,index =true ,store = true)
    private String saleScore;

    /**
     * 销量评分
     */
    @Field(type = FieldType.Double ,index =true,store = true)
    private String stock;

    /**
     * 有无缩略图
     */
    @Field(type = FieldType.Double ,index =true, store = true)
    private String thumb;

    /**
     * 总销量
     */
    @Field(type = FieldType.Double ,index =true, store = true)
    private String saleAmount;

    /**
     * 总点击量
     */
    @Field(type = FieldType.Double ,index =true ,store = true)
    private String clickAmount;

    /**
     * 总好评量
     */
    @Field(type = FieldType.Double ,index =true,store = true)
    private String goodEvalAmount;

    /**
     * 售价
     */
    @Field(type = FieldType.Double ,index =true,store = true)
    private String ecPrice;
    /**
     * 可用库存
     */
    @Field(type = FieldType.Double ,index =true, store = true)
    private String availableStock;

    /**
     * 排名
     */
    @Field(type = FieldType.Double ,index =true, store = true)
    private String productOrder;
    /**
     * 评论条数
     */
    @Field(type = FieldType.Double ,index =true, store = true)
    private String commentSum;
    /**
     * 该商品参加的促销类型id,多个id,用逗号分隔组成string
     */
    @Field(type = FieldType.Double ,index =true,store = true)
    private String promoteIds;

    // ======================存储+不索引+Double类型===========================

    /**
     * 市场价
     */
    @Field(type = FieldType.Double ,index =true ,store = true)
    private String marketPrice;

    // ======================存储+不索引+String类型===========================
    /**
     * 最小订购量
     */
    @Field(type = FieldType.Keyword ,index =true ,store = true)
    private String productLeastOrder;
    /**
     * 最大订购量
     */
    @Field(type = FieldType.Keyword ,index =true ,store = true)
    private String orderLimitAmount;
    /**
     * 促销信息
     */
    @Field(type = FieldType.Keyword ,index =true ,store = true)
    private String promotePhrase;
    /**
     * 图标路径
     */
    @Field(type = FieldType.Keyword ,index =true ,store = true)
    private String tagIconUrl;
    /**
     * 产品ID
     */
    @Field(type = FieldType.Keyword ,index =true ,store = true)
    private String productId;
    /**
     * 商品ID
     */
    @Field(type = FieldType.Keyword ,index =true ,store = true)
    private String goodsId;

    /**
     * 商品ID
     */
    @Field(type = FieldType.Keyword ,index =true ,store = true)
    private String goodsNo;
    /**
     * 图片路径
     */
    @Field(type = FieldType.Keyword ,index =true ,store = true)
    private String proImageUrl;
    /**
     * 商品卖家（1：健一网商品；2：香港华润堂）
     */
    @Field(type = FieldType.Keyword ,index =true ,store = true)
    private String goodsSeller;
    /**
     * 商品类型
     */
    @Field(type = FieldType.Keyword ,index =true,store = true)
    private String drugPrescriptionType;

}
