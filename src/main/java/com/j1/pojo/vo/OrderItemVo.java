package com.j1.pojo.vo;

import com.j1.pojo.BaseBO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;

/**
 * Created by wangchuanfu on 20/8/10.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "order_info", type = "#{esAttribute.indexType}", shards = 5, replicas = 1)

public class OrderItemVo extends BaseBO {

    /**
     * 主键
     */
    @Field(type = FieldType.Keyword ,index =true ,store = false)
    private Long orderItemId;
    /**
     * 订单ID
     */
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private Long orderId;
    /**
     * 商品类型
     */
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String goodsType;
    /**
     * 货品ID
     */
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private Long goodsId;
    /**
     * 商品编码
     */
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String goodsNo;
    /**
     * 商品名称
     */
    @Field(type = FieldType.Text ,index =true, analyzer="ik_max_word",    searchAnalyzer = "ik_max_word"  ,store = true)
    private String goodsName;

    /**
     * 成交价
     */
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private BigDecimal goodsPrice;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    /**
     * 商品数量
     */
    private BigDecimal goodsAmount;
    /**
     * 小计金额
     */
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private BigDecimal goodsSumFee;


    /**
     * 商品图片的URL
     */
    @Field(type = FieldType.Text ,index =true ,store = false)

    private String proImageUrl;
    /**
     * 原始图片路径
     */

    @Field(type = FieldType.Keyword ,index =false ,store = false)

    private String producer;

    /**
     * 商品ID
     */
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private Long productId;

    /**
     * 药品处方类型
     */
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String drugPrescriptionType;

    // 订单状态
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private Integer orderState;



}
