package com.j1.pojo.pojo;

import com.j1.pojo.BaseBO;
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
   // private String deliveryNotes;
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

    private String standbyTime;   //判断库存是否同步
    /**
     * 接收orderID
     */
    private String strOrderID;
    // 赠品的goodsId
    private Long giftGoodsId;
    // 赠品的productId
    private Long giftProductId;
    // 订单提交时间
    private String commitTime;
    // 订单状态
    private Integer orderState;
    // 订单状态名称
    private String codeValue;
    // 订单状态编码
    private String codeNo;
    private String isvalid;
    private String isTc ;
    private Long memberId;
    private String stockNo; //仓库编码
    /**
     * 查询条件是否查询所有订单，包括处方药和非处方药
     * 前台我的订单明细专用
     * Y:无限制
     * N:非处方药
     */
    private String isAll;

    private String goodsBiaoshi;//商品标识

    //保税仓Id,对应表stock主键
    private Integer stockId;

    //是否在优惠券黑名单里面
    private Integer inCouponBlack;

}
