package com.j1.pojo.vo;
import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wangchuanfu on 20/8/14.
 */
public class OrderUnionSearchFiled {
    /**
     * 订单ID主键
     */
    @Field(type = FieldType.Integer, store = true)
    private Integer orderId;

    /**
     * 订单编号
     */
    @Field(type = FieldType.Keyword, store = true)
    private String flowId;

    /**
     * 采购商名称
     */
    @Field(type = FieldType.Text, store = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String custName;

    /**
     * 采购商ID-买家
     */
    @Field(type = FieldType.Integer, store = true)
    private Integer custId;

    /**
     * 供应商名称
     */
    @Field(type = FieldType.Text, store = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String supplyName;

    /**
     * 供应商ID
     */
    @Field(type = FieldType.Integer, store = true)
    private Integer supplyId;

    /**
     * 取消原因
     */
    @Field(type = FieldType.Text, store = true)
    private String cancelResult;

    /**
     * 订单优惠后金额
     */
    @Field(type = FieldType.Scaled_Float, store = true)
    private BigDecimal orgTotal;

    /**
     * 运费金额
     */
    @Field(type = FieldType.Scaled_Float, store = true)
    private BigDecimal freight;

    /**
     * 订单总金额
     */
    @Field(type = FieldType.Scaled_Float, store = true)
    private BigDecimal orderTotal;

    /**
     * 最后支付金额
     */
    @Field(type = FieldType.Scaled_Float, store = true)
    private BigDecimal finalPay;

    /**
     * 商品总数量
     */
    @Field(type = FieldType.Integer, store = true)
    private Integer totalCount;

    /**
     * 账期还款状态 0 未还款  1 已还款（二期）
     */
    @Field(type = FieldType.Integer, store = true)
    private Integer paymentTermStatus;

    /**
     * 订单状态
     */
    @Field(type = FieldType.Keyword, store = true)
    private String orderStatus;

    /**
     * 备注信息
     */
    @Field(type = FieldType.Text, store = true)
    private String remark;

    /**
     * 买家留言
     */
    @Field(type = FieldType.Text, store = true)
    private String leaveMessage;

    /**
     * 订单合并付款ID
     */
    @Field(type = FieldType.Integer, store = true)
    private Integer orderCombinedId;

    /**
     * 卖家是否确认结算
     */
    @Field(type = FieldType.Keyword, store = true)
    private String confirmSettlement;

    /**
     * 结算金额
     */
    @Field(type = FieldType.Scaled_Float, store = true)
    private BigDecimal settlementMoney;

    /**
     * 满减金额
     */
    @Field(type = FieldType.Scaled_Float, store = true)
    private BigDecimal preferentialMoney;

    /**
     * 优惠金额备注
     */
    @Field(type = FieldType.Text, store = true)
    private String preferentialRemark;

    /**
     * 支付状态
     */
    @Field(type = FieldType.Keyword, store = true)
    private String payStatus;

    /**
     * 支付类型表ID
     */
    @Field(type = FieldType.Integer, store = true)
    private Integer payTypeId;

    /**
     * 订单生成时间
     */
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 支付时间
     */
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    /**
     * 取消时间
     */
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date cancelTime;

    /**
     * 发货时间
     */
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date deliverTime;

    /**
     * 收货时间
     */
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date receiveTime;

    /**
     * 结算时间
     */
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date settlementTime;

    /**
     *
     */
    @Field(type = FieldType.Text, store = true)
    private String createUser;

    /**
     * 订单最后更新时间
     */
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     *
     */
    @Field(type = FieldType.Text, store = true)
    private String updateUser;

    /**
     * 发票类型 1 增值税专用发票 2 增值税普通发票
     */
    @Field(type = FieldType.Keyword, store = true)
    private Integer billType;

    /**
     * 延期收货日志（时间 第n次延期）
     */
    @Field(type = FieldType.Text, store = true)
    private String delayLog;

    /**
     *
     */
    @Field(type = FieldType.Integer, store = true)
    private Integer delayTimes;

    /**
     * 1,买家确认收货 （8 14）2,系统自动确认收货（11）此字段没意义 根据订单状态
     */
    @Field(type = FieldType.Keyword, store = true)
    private Integer receiveType;

    /**
     * 订单账期(单位：天) 二期
     */
    @Field(type = FieldType.Integer, store = true)
    private Integer paymentTerm;

    /**
     * 订单支付标记 1：打款成功 2：打款失败 3：退款成功 4：退款失败
     */
    @Field(type = FieldType.Keyword, store = true)
    private String payFlag;

    /**
     * 订单商品种类数量
     */
    @Field(type = FieldType.Integer, store = true)
    private Integer productSortCount;

    /**
     * 订单来源 1 pc 2 安卓 3 IOS 4 H5  5 erp下单
     */
    @Field(type = FieldType.Keyword, store = true)
    private Integer source;

    /**
     * 订单类型：0.普通订单；1.一起购订单
     */
    @Field(type = FieldType.Keyword, store = true)
    private Integer orderType;

    /**
     * 销售顾问编码
     */
    @Field(type = FieldType.Text, store = true)
    private String adviserCode;

    /**
     * 销售顾问姓名
     */
    @Field(type = FieldType.Text, store = true)
    private String adviserName;

    /**
     * 销售顾问手机号码
     */
    @Field(type = FieldType.Text, store = true)
    private String adviserPhoneNumber;

    /**
     * 销售顾问备注
     */
    @Field(type = FieldType.Text, store = true)
    private String adviserRemark;

    /**
     * 订单有效标记 1 已删除 0 未删除
     */
    @Field(type = FieldType.Keyword, store = true)
    private Integer deleteFlag;

    /**
     * 包含多个促销名称,用,分割
     */
    @Field(type = FieldType.Text, store = true)
    private String promotionName;

    /**
     * 卖家发货是部分发货,且不补发货物,剩下的货物金额(不含优惠金额)
     */
    @Field(type = FieldType.Scaled_Float, store = true)
    private BigDecimal cancelMoney;

    /**
     * 卖家发货是部分发货,所发货物金额(不含优惠金额)
     */
    @Field(type = FieldType.Scaled_Float, store = true)
    private BigDecimal deliveryMoney;

    /**
     * 卖家是否部分发货,1-是,0-否
     */
    @Field(type = FieldType.Keyword, store = true)
    private String isPartDelivery;

    /**
     * 部分发货说明
     */
    @Field(type = FieldType.Text, store = true)
    private String partDeliveryRemark;

    /**
     * 卖家发货是部分发货,且不补发货物,剩下的货物均摊后的金额
     */
    @Field(type = FieldType.Scaled_Float, store = true)
    private BigDecimal preferentialCancelMoney;

    /**
     * 卖家发货是部分发货,所发货物均摊后的金额
     */
    @Field(type = FieldType.Scaled_Float, store = true)
    private BigDecimal preferentialDeliveryMoney;

    /**
     * 满赠积分
     */
    @Field(type = FieldType.Scaled_Float, store = true)
    private BigDecimal promotionPoint;

    /**
     * 平台积分
     */
    @Field(type = FieldType.Scaled_Float, store = true)
    private BigDecimal platformPoint;

    /**
     * 满赠礼品
     */
    @Field(type = FieldType.Text, store = true)
    private String promotionGift;

    /**
     * 是否是换购订单:1-是,0-否
     */
    @Field(type = FieldType.Keyword, store = true)
    private Integer isPromotionChange;

    /**
     * 父亲订单节点
     */
    @Field(type = FieldType.Keyword, store = true)
    private Integer parentOrderId;

    /**
     * 优惠券金额
     */
    @Field(type = FieldType.Scaled_Float, store = true)
    private BigDecimal couponMoney;

    /**
     * 集团erp订单编码
     */
    @Field(type = FieldType.Keyword, store = true)
    private String erpOrderId;

    /**
     * 组团状态：0（组团成功待下发）；1（组团成功确认）
     */
    @Field(type = FieldType.Keyword, store = true)
    private Integer groupStatus;

    /**
     * 是否代付：0（否);1 （是）
     */
    @Field(type = FieldType.Keyword, store = true)
    private Integer paidByOthers;

    /**
     * 拼团Id
     */
    @Field(type = FieldType.Integer, store = true)
    private Integer fightTogetherId;

    /**
     * 0:默认；1：组团成功； 2：组团失败
     */
    @Field(type = FieldType.Keyword, store = true)
    private Integer fightTogetherStatus;

    /**
     * 采购商省份编码
     */
    @Field(type = FieldType.Keyword, store = true)
    private String custProvince;

    /**
     * 采购商城市编码
     */
    @Field(type = FieldType.Keyword, store = true)
    private String custCity;

    /**
     * 采购商区编码
     */
    @Field(type = FieldType.Keyword, store = true)
    private String custDistrict;

    /**
     * 取消原因类型
     */
    @Field(type = FieldType.Keyword, store = true)
    private Integer cancelReasonType;

    /**
     * 其他取消原因
     */
    @Field(type = FieldType.Text, store = true)
    private String otherCancelReason;

    /**
     * 订单父单标识 1：父单  0：子单
     */
    @Field(type = FieldType.Keyword, store = true)
    private Integer parentOrderFlag;

    /**
     * 子单的父单主键id
     */
    private Integer parentId;

    /**
     * 订单使用的购物金金额
     */
    @Field(type = FieldType.Scaled_Float, store = true)
    private BigDecimal shopRechargeMoney;

    /**
     * 卖家发货是部分发货,未发货物的购物金
     */
    @Field(type = FieldType.Scaled_Float, store = true)
    private BigDecimal rechargeCancelMoney;

    //============================================订单主表t_order信息 结束============================================

    //============================================订单子表t_order_child信息 开始============================================
    /**
     * 订单子表ID主键
     */
    @Field(type = FieldType.Integer, store = true)
    private Integer orderChildId;

    /**
     * 闪购ID,以","分割
     */
    @Field(type = FieldType.Keyword, store = true)
    private String shangouId;

    /**
     * 订单使用的返利金抵扣金额
     */
    @Field(type = FieldType.Scaled_Float, store = true)
    private BigDecimal useRebateMoney;

    /**
     * 订单可获得的返利金额
     */
    @Field(type = FieldType.Scaled_Float, store = true)
    private BigDecimal getRebateMoney;

    /**
     * 已返还的采购返利
     */
    @Field(type = FieldType.Scaled_Float, store = true)
    private BigDecimal pushRebateMoney;

    /**
     * 确认收货后72小时是否已推送采购返利，0-未推送，1-已推送，2-退货订单全部完成后已推送补偿返利金
     */
    @Field(type = FieldType.Keyword, store = true)
    private String pushRebateStream;

    /**
     * 是否有包裹后续添加，0代表有，1代表没有
     */
    @Field(type = FieldType.Keyword, store = true)
    private String isHashpack;

    /**
     *
     */
    @Field(type = FieldType.Keyword, store = true)
    private String courierNumber;

    /**
     * 订单买卖家是否交换了首营资质 0:是 1:否
     */
    @Field(type = FieldType.Integer, store = true)
    private Integer isFirstOrder;

    /**
     *
     */
    @Field(type = FieldType.Text, store = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String bdName;

    /**
     *
     */
    @Field(type = FieldType.Keyword, store = true)
    private String bdPhone;

    /**
     * 卖家备注
     */
    @Field(type = FieldType.Text, store = true)
    private String sellerRemark;

    /**
     * 汇款信息
     */
    @Field(type = FieldType.Text, store = true)
    private String remitInfo;

    /**
     * 省份类型(默认0省内1省外2)
     */
    @Field(type = FieldType.Integer, store = true)
    private Integer provinceType;

    /**
     * 是否首单客户(0:是 1:不是)
     */
    @Field(type = FieldType.Keyword, store = true)
    private String isNewCustomer;

    /**
     * 客户类型
     */
    @Field(type = FieldType.Keyword, store = true)
    private String customerType;

    /**
     * 开单BD id
     */
    @Field(type = FieldType.Integer, store = true)
    private Integer createOrderBdId;

    /**
     * 开单BD 名称
     */
    @Field(type = FieldType.Text, store = true)
    private String createOrderBdName;

    /**
     * mp退款运费标记(默认0 不退运费; 1退运费)
     */
    @Field(type = FieldType.Byte, store = true)
    private Byte refundFreightFalg;

    /**
     * 下单用户ip信息
     */
    @Field(type = FieldType.Ip, store = true)
    private String ipAddress;

    /**
     * app设备uuid
     */
    @Field(type = FieldType.Text, store = false, index = false)
    private String appUuid;

    /**
     * 是否绑定合伙人,0-未知，1-未绑定；2-已绑定
     */
    @Field(type = FieldType.Byte, store = true)
    private Byte isBindingPartner;

    /**
     * erp订单号
     */
    @Field(type = FieldType.Keyword, store = true)
    private String erpOrderCode;

    /**
     * BD人员手机号码
     */
    @Field(type = FieldType.Keyword, store = true)
    private String bdPhoneEncrypt;

    /**
     * 电子发票url
     */
    @Field(type = FieldType.Keyword, store = true, index = false)
    private String billUrl;

    /**
     * 投诉标志，0-未投诉；1-已投诉
     */
    @Field(type = FieldType.Boolean, store = true)
    private Boolean complaintFlag;

    /**
     * 卖家给买家留言标志，0-无留言；1-存在留言
     */
    @Field(type = FieldType.Boolean, store = true)
    private Boolean buyerRemarkFlag;

    /**
     * 订单未付款系统自动取消的时间
     */
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date nopayCancelTime;

    /**
     * 订单商品占用库存情况，0-组货中；1-成功
     */
    @Field(type = FieldType.Integer, store = true)
    private Integer inventoryStatus;

    /**
     * 订单最长预计到货时间
     */
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date arrivalTime;

    /**
     *
     */
    @Field(type = FieldType.Integer, store = true)
    private Integer cityPartnerId;

    /**
     *
     */
    @Field(type = FieldType.Text, store = true)
    private String cityPartnerName;

    /**
     * 电销人员id
     */
    @Field(type = FieldType.Integer, store = true)
    private Integer teleId;

    /**
     * 电销人员名称
     */
    @Field(type = FieldType.Text, store = true)
    private String teleName;

    /**
     * 后台下单是否是近效期，0-非近效期；1-近效期
     */
    @Field(type = FieldType.Integer, store = true)
    private Integer validityFlag;

    /**
     * 订单是否已评价 0:未评价，1:已评价
     */
    @Field(type = FieldType.Keyword, store = true)
    private String evaluateFlag;

    /**
     * 是否打印销售合同
     */
    @Field(type = FieldType.Integer, store = true)
    private Integer isPrintContract;

    /**
     * 已推送的平台采购返利
     */
    @Field(type = FieldType.Scaled_Float, store = true)
    private BigDecimal pushPlatformMoney;

    /**
     * cpsBdId
     */
    @Field(type = FieldType.Keyword, store = true)
    private String cpsBdId;
}
