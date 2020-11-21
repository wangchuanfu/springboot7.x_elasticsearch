package com.j1.pojo.vo;

/**
 * Created by wangchuanfu on 20/8/14.
 */
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "b2b_order_detail", shards = 5, replicas = 1)
public class OrderDetailSearchFiled {



        /**
         *
         */
        @Field(type = FieldType.Integer, store = true)
        private Integer orderDetailId;

        /**
         * 订单ID主键
         */
        //private Integer orderId;

        /**
         * 商品主数据ID
         */
        @Field(type = FieldType.Integer, store = true)
        private Integer skuId;

        /**
         * 商品规格
         */
        @Field(type = FieldType.Text, store = true)
        private String specification;

        /**
         * 供应商ID
         */
        //private Integer supplyId;

        /**
         * 商品ID
         */
        @Field(type = FieldType.Integer, store = true)
        private Integer productId;

        /**
         * 品名
         */
        @Field(type = FieldType.Text, store = true)
        private String brandName;

        /**
         * 剂型
         */
        @Field(type = FieldType.Text, store = true)
        private String formOfDrug;

        /**
         *
         */
        @Field(type = FieldType.Text, store = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
        private String productName;

        /**
         * 商品编码(供应商、本公司商品编码)
         */
        @Field(type = FieldType.Keyword, store = true)
        private String productCode;

        /**
         *
         */
        @Field(type = FieldType.Integer, store = true)
        private Integer manufacturesId;

        /**
         * 生产厂家
         */
        @Field(type = FieldType.Text, store = true)
        private String manufactures;

        /**
         * 商品单价
         */
        @Field(type = FieldType.Scaled_Float, store = true)
        private BigDecimal productPrice;

        /**
         * 实际结算价，正常情况下与销售单价一致
         */
        @Field(type = FieldType.Scaled_Float, store = true)
        private BigDecimal productSettlementPrice;

        /**
         * 购买单品数量
         */
        @Field(type = FieldType.Integer, store = true)
        private Integer productCount;

        /**
         * 确认收到药品数量
         */
        @Field(type = FieldType.Integer, store = true)
        private Integer recieveCount;

        /**
         *
         */
        @Field(type = FieldType.Text, store = false, index = false)
        private String remark;

        /**
         *
         */
        @Field(type = FieldType.Text, store = true)
        private String createUser;

        /**
         * 记录生成时间
         */
        @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        private Date createTime;

        /**
         *
         */
        @Field(type = FieldType.Text, store = true)
        private String updateUser;

        /**
         * 记录更新时间
         */
        @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        private Date updateTime;

        /**
         *
         */
        @Field(type = FieldType.Keyword, store = true)
        private String shortName;

        /**
         * SPU编码(一号药城商品编码)
         */
        @Field(type = FieldType.Keyword, store = true)
        private String spuCode;

        /**
         * 只保存特价活动的促销的id,没参加为空
         */
        private Integer promotionId;

        /**
         * 活动名称
         */
        @Field(type = FieldType.Text, store = true)
        private String promotionName;

        /**
         * 该字段保存商品参加的所有促销的id,格式a,b,c
         */
        @Field(type = FieldType.Text, store = true)
        private String promotionCollectionId;

        /**
         * 该字段保存商品参加的所有促销的类型,格式a,b,c
         */
        @Field(type = FieldType.Text, store = true)
        private String promotionCollectionType;

        /**
         * 该字段保存参加满减优惠的金额,格式12,23,45
         */
        @Field(type = FieldType.Text, store = true)
        private String preferentialCollectionMoney;

        /**
         *
         */
        @Field(type = FieldType.Integer, store = true)
        private Integer cancelProduceNum;

        /**
         * 单个商品的运费
         */
        @Field(type = FieldType.Scaled_Float, store = true)
        private BigDecimal freight;

        /**
         * 品种的总运费
         */
        @Field(type = FieldType.Scaled_Float, store = true)
        private BigDecimal freightTotal;

        /**
         * 商品正常价格,商品有可能参加了特价活动或者换购过来的,该字段保存的是原价
         */
        @Field(type = FieldType.Scaled_Float, store = true)
        private BigDecimal productNormalPrice;

        /**
         * 优惠券金额
         */
        @Field(type = FieldType.Scaled_Float, store = true)
        private BigDecimal couponMoney;

        /**
         * 最小包装单位
         */
        @Field(type = FieldType.Text, store = true)
        private String unit;

        /**
         * 单位描述
         */
        @Field(type = FieldType.Text, store = true)
        private String unitDes;

        /**
         * 预留号
         */
        @Field(type = FieldType.Keyword, store = true)
        private String reserveNo;

        /**
         * 仓库编码
         */
        @Field(type = FieldType.Keyword, store = true)
        private String storageCode;

        /**
         * 商品的返利金抵扣均摊
         */
        @Field(type = FieldType.Scaled_Float, store = true)
        private BigDecimal shareUseRebateMoney;

        /**
         * 商品参加的返利活动 id
         */
        @Field(type = FieldType.Text, store = true)
        private String promotionRebateId;

        /**
         * 商品可获得的返利金
         */
        @Field(type = FieldType.Scaled_Float, store = true)
        private BigDecimal getRebateMoney;

        /**
         * 促销类型
         */
        @Field(type = FieldType.Keyword, store = true)
        private String promotionType;

        /**
         * 此商品已返返利金
         */
        @Field(type = FieldType.Scaled_Float, store = true)
        private BigDecimal pushRebateMoney;

        /**
         * 推荐列表提交的商品
         */
        @Field(type = FieldType.Text, store = true)
        private String mdInfo;

        /**
         * 商品类型，0-普通品，1-集采商品
         */
        @Field(type = FieldType.Keyword, store = true)
        private String productType;

        /**
         *
         */
        @Field(type = FieldType.Scaled_Float, store = true)
        private BigDecimal payPrice;

        /**
         * 公开价
         */
        @Field(type = FieldType.Scaled_Float, store = true)
        private BigDecimal channelPrice;

        /**
         * 集采商品询价版本号
         */
        @Field(type = FieldType.Text, store = true)
        private String wisdomBuyVersion;

        /**
         * 实付单价（四位精度）
         */
        @Field(type = FieldType.Scaled_Float, store = true)
        private BigDecimal payPrice4;

        /**
         * 商品参加的返利活动 id
         */
        @Field(type = FieldType.Scaled_Float, store = true)
        private BigDecimal originPrice;

        /**
         * 订单商品占用库存情况，0-组货中；1-成功
         */
        @Field(type = FieldType.Integer, store = true)
        private Integer inventoryStatus;

        /**
         * 组货中商品预计到货天数
         */
        @Field(type = FieldType.Integer, store = true)
        private Integer arrivalTime;

        /**
         * 一品多供 供货商id
         */
        @Field(type = FieldType.Integer, store = true)
        private Integer supplierId;

        /**
         * 一品多供 虚拟商家id
         */
        @Field(type = FieldType.Integer, store = true)
        private Integer fictitiousId;

        /**
         * 开始有效期
         */
        @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        private Date validityDateStart;

        /**
         * 结束有效期
         */
        @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        private Date validityDateEnd;

        /**
         * 商品供应商
         */
        @Field(type = FieldType.Keyword, store = true)
        private String sellerName;

        /**
         * 底价活动id
         */
        @Field(type = FieldType.Integer, store = true)
        private Integer floorPriceId;

        /**
         * 商品购物金均摊
         */
        @Field(type = FieldType.Scaled_Float, store = true)
        private BigDecimal shopRechargeMoneyShare;
        /**
         * 订单信息
         */
        @Field(type = FieldType.Nested, store = true)
        private OrderUnionSearchFiled orderInfo;
}
