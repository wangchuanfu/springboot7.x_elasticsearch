package com.j1.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by wangchuanfu on 20/8/10.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods extends BaseBO {

    // 是否存在手机促销
    private Boolean isMobilePromote;
    // 商品限购数量
    private int limitsBuyNum;
    // 手机促销信息
    private List<Goods> mobilePromoteList;
    // 商品ID
    private Long goodsId;
    // 商品编号
    private String goodsNo;
    // 产品ID
    private Integer productId;
    // 商品名称
    private String goodsName;
    // 商品描述
    private String goodsDesc;
    // 所在仓库ID
    private Integer stockId;
    // 供应商ID
    private Integer supplyId;
    // 总库存
    private BigDecimal goodStock;
    // 备货库存
    private BigDecimal prepareStock;
    // 锁定库存
    private BigDecimal lockStock;
    // 可用库存
    private BigDecimal availableStock;
    // 成本价
    private BigDecimal costPrice;
    // 网售价
    private BigDecimal ecPrice;
    // 市场价
    private BigDecimal marketPrice;
    // 条形码
    private String barCode;
    // 二维条码
    private String doubleBarCode;
    // 重量
    private BigDecimal weight;
    // 体积
    private BigDecimal volume;
    // 运费计算方式
    private String freightType;
    // 生产日期
    private String goodsManufactureDate;
    // 保质截止日期
    private String goodsLimitDate;
    // 包装规格
    private String packageStandard;
    // 点击量
    private Long clickAmount;
    // 销售量
    private Long saleAmount;
    private String isDefault;

    private Integer promoteConditionGoodsId;

    private String proImageUrl;
    private String producer;

    private BigDecimal orderAmount;
    private BigDecimal sumPrice;
    private Integer orderLimitAmount;
    private BigDecimal productLeastOrder;
    private String proCatalogId;
    // 分类级别 1.2.3
    private String catalogLevel;

    // 计算过促销后的最终单价
    private BigDecimal finalPrice;
    // 淘宝商品优惠信息
    // 淘宝订单优惠信息
    // 赠品列表
    private List<GoodsGift> goodsGiftList;
    private String isGroup;
    private String tyingGoodsIds;
    private String typingGoodsPrice;
    // 版本号(递增+1)，起乐观锁作用
    private Long version;
    // 税金
    private BigDecimal taxFee;
    // 税率
    private BigDecimal taxProbability;
    // 淘宝商品SKUID
    private String sku_iids;
    // 购买数量
    private String nums;
    // 淘宝货号
    private String topNumId;

    // 购物车显示用GoodsId(隐形眼睛L,R)
    private String goodsIdForView;
    private Integer ids;

    private String productTypeName;
    private String unitName;

    private String discountState;
    private String promoteRuleIds;
    private String promoteUpdateTime;
    private String promotePhrase;
    private String beginTime;
    private String endTime;
    private BigDecimal discountPrice;
    private BigDecimal discountStock;
    private BigDecimal memberBuyLimit;
    private String gifts;
    private String giftAmount;
    private Integer returnPoints;
    private String returnVouchers;
    private String vouchersAmount;
    private String isDeliveryFree;
    private String memberRanks;
    private String memberDisacount;
    private String stand;

    private String isOnsale;

    // 处方类型
    private String drugPrescriptionType;

    //处方药用法信息
    private EcTcDoseExplan ecTcDoseExplan;

    private ProductGroupSku sku;

    // 名称简写，目前用于搜索资讯
    private String goodsShortName;

    // 商品参加的促销规则ID
    private String allPromoteRuleIds;

    private BigDecimal discountPrice2; // 优惠价格（王军）

    /*
     * add by zp 2014-05-05
     */
    private Integer productUV;
    private Integer productPV;

    private Integer allUV;
    private Integer allPV;

    /*
     * add by zp 2014-05-05
     */

    /*
     * add by dongqing 2014-06-09 隐形眼镜使用
     */
    private String degree; // 隐形眼镜度数
    private String productCommonName; // 通用名称

    // 是否可以使用优惠卷
    private String canCoupon;

    private String canHdfk; // 是否支持货到付款

    private String productCode;

    /**
     * 非数据库字段
     */
    private String goodsUrl;
    /**
     * 图片集合
     */
    private List<String> imageUrlList;
    /**
     * 国药准字号
     */
    private String drugApproveNo;

    /**
     * 来源渠道
     */
    private Integer multi;

    // 隐形眼镜光度
    private String eyeStand;
    /**
     * 是否是A类商品
     */
    private String isA;
    /**
     * 是否是top类商品
     */
    private String isTop;
    /**
     * 运费分类
     */
    private String carryType;
    /**
     * 商品品牌名称
     */
    private String productBrandName;
    private String productBrandId;
    private String productName;

    private String oneLevelCatalog;

    private String twoLevelCatalog;

    private String threeLevelCatalog;

    private String proYwCatalogId;

    private String productKeyword;

    private Integer addTimeMin;
    private Integer editTimeMin;

    // 搭售商品列表
    private List<EcPromoteRuleGoodsTNew> goodstList;
    // 单品送赠品最大数量
    private BigDecimal giftMaxCount;
    // 单品搭售商品最大数量
    private BigDecimal hgMaxCount;

    // 当前参加的促销详情
    private Integer ruleItemId;
    // 订单详情ID
    private Long orderItemId;

    private String[] exceptGoodsIds;
    private String[] proCatalogIds;
    private String[] goodsIds;
    private String[] skus;
    private String[] proYwCatalogIds;
    // 促销详情内容集合
    private List<Map<String, Object>> itemNameList;
    /*
     * 礼品盒商品ID
     */
    private Long boxId;
    /*
     * 商品类型（购物车用）
     */
    private String goodsType;
    /*
     * 免费频道商品ID
     */
    private Long freeGoodsId;
    /*
     * 商品所需积分
     */
    private BigDecimal goodsPoints;
    private BigDecimal sumGoodsPoints;
    /*
     * 商品是否参加促销
     */
    private String isPromote;
    /*
     * 是否选中
     */
    private String isSelected;
    /*
     * 购物车商品状态(1.价格;2.缺货;3.下架)
     */
    private String cartGoodsState;
    /*
     * 特权信息
     */
    private Map<String, Object> freedomMap;
    /*
     * 商品特权价
     */
    private BigDecimal freedomFee;
    /*
     * 特权优惠金额
     */
    private BigDecimal freedomDiscountFee;
    /*
     * 特权优惠总金额
     */
    private BigDecimal sumFreedomDiscountFee;
    /*
     * 该商品是否在标签中
     */
    private String hasInlabel;
    /*
     * 促销标签集合
     */
    private List<GoodsActivityLabel> promoteLableList;
    /*
     * 活动标签集合
     */
    private List<GoodsActivityLabel> activityLableList;
    /**
     * 功效
     */
    private String drugTreatment;

    /**
     * 是否为华润堂商品
     */
    private String goodsSeller;
    /**
     * 商品总税费
     */
    private BigDecimal sumTaxFee;
    /**
     * 仓库名称
     */
    private String stockName;

    /**
     * 积分赠送
     */
    private Integer giftPoints;


    // 支付卡黑名单提示(多个用、隔开)
    private String blackPaymentPrompt;
    /**
     * 仓库Number
     */
    private String stockNo;
}
