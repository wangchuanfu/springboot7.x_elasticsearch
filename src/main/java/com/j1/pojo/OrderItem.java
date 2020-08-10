package com.j1.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by wangchuanfu on 20/8/10.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem extends BaseBO {

    /**
     * 主键
     */
    private Long orderItemId;
    private String goodsWxId;

    private Long sealedMemberId;

    /**
     * 订单ID
     */
    private Long orderId;
    /**
     * 商品类型
     */
    private String goodsType;
    /**
     * 货品ID
     */
    private Long goodsId;
    /**
     * 商品编码
     */
    private String goodsNo;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 单位
     */
    private String goodsUnit;
    /**
     * 原价
     */
    private BigDecimal goodsOldPrice;
    /**
     * 成交价
     */
    private BigDecimal goodsPrice;
    /**
     * 商品数量
     */
    private BigDecimal goodsAmount;
    /**
     * 小计金额
     */
    private BigDecimal goodsSumFee;
    /**
     * 商品的宽
     */
    private BigDecimal weight;
    /**
     * 商品的体积
     */
    private BigDecimal volume;
    /**
     * 商品库存
     */
    private BigDecimal goodStock;
    /**
     * 商品图片的URL
     */
    private String proImageUrl;
    /**
     * 原始图片路径
     */
    private String imageOrign;
    private String producer;
    /**
     * 支付介绍
     */
    private String deliveryNotes;
    /**
     * 商品ID
     */
    private Long productId;
    /**
     * 成本价
     */
    private BigDecimal goodsCostPrice;
    /**
     * 药品处方类型
     */
    private String drugPrescriptionType;
    /**
     * 标准
     */
    private String packageStandard;
    /**
     * 多个ID
     */
    private String ids;
    /**
     * 组合套餐ID
     */
    private Integer productCombinationId;
    /**
     * 订单ID的集合
     */
    private String[] orderIDS;
    /**
     * 接收orderID
     */
    private String strOrderID;
    // 赠品的goodsId
    private Long giftGoodsId;
    // 赠品的productId
    private Long giftProductId;
    /*
     * 会员识别码
     */
    private String memberKey;
    /*
     * 会员ID
     */
    private Long memberId;
    //来源
    private String mul;
    //是否处方药
    private String isTc;
    //优惠平摊后的商品价格
    private BigDecimal goodsPrice2;
    //商品分摊优惠金额
    private BigDecimal goodsPrice2Ft;

    private Integer stockId;
    /**
     * 税金
     */
    private BigDecimal taxFee;
    /**
     * 税率
     */
    private BigDecimal taxProbability;

    // 积分赠送
    private Integer giftPoints;

    //是否在优惠券黑名单
    private Integer inCouponBlack;

    //商品标识
    private String goodsBiaoshi;
}
