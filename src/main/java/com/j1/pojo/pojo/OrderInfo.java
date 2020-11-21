package com.j1.pojo.pojo;

import com.j1.pojo.BaseBO;
import com.j1.pojo.Goods;
import com.j1.pojo.GoodsGift;
import com.j1.pojo.OrderItem;
import com.j1.pojo.ProductYwCatalog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangchuanfu on 20/8/10.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo extends BaseBO {

    private Long sealedMemberId;
    private String receiverCardno;
    private String goodsSeller;
    private Long orderId;
    private String erpOrderId;
    private Long memberId;
    private Long parentOrderId;
    private Integer multiChannelId;
    private String multiChannelName;
    private String isPaid;
    private String paymentMode;
    private Integer paymentTypeId;
    private String paymentNo;
    private String paidTime;
    private Integer offlinePaidUserId;
    private Integer logisticCompanyId;
    private Integer orderState;
    private Integer orderOutState;
    private String limitDate;
    private BigDecimal skuFee;
    private BigDecimal discountFee;
    private Integer orderPoints;
    private BigDecimal orderVouchers;
    private BigDecimal otherDiscounts;
    private BigDecimal deliveryFee;		//运费
    private BigDecimal realDeliveryFee;//实际运费
    private BigDecimal taxFee;//税金
    private BigDecimal realTaxFee;//实际税金
    private BigDecimal orderFee;
    private String orderMsg;
    private String orderNotes;
    private String auditNotes;
    private Integer auditUserId;
    private String auditTime;
    private String isLock;
    private String lockTime;
    private Integer lockUserId;
    private String commitTime;
    private String finishTime;
    private String cancelTime;
    private Integer cancelUserId;
    private String cancelNotes;
    private BigDecimal skuWeights;
    private BigDecimal skuVolume;
    private String isIntoErp;
    private String intoTime;
    private String receiveUser;
    private Integer provinceId;
    private Integer cityId;
    private Integer areaId;
    private String receiveAddress;
    private String receiveFullAddress;
    private Integer receivePost;
    private String receiveTel;
    private String receiveMobile;
    private String receiveEmail;
    private String receiveDateType;
    private String receiveTimeType;
    private String invoiceType;
    private String invoiceTitle;
    private String invoiceContent;
    private String invoiceTaxNo;
    private String invoiceTaxAddress;
    private String invoiceTaxTel;
    private String invoiceTaxBank;
    private String invoiceTaxAccount;
    private Long multiChannelOrderId;
    private Long multiChannelOrderBatchId;
    private BigDecimal paidFee;
    private String invoiceContentType;
    private BigDecimal orderPayFee;//订单待支付金额

    private String isCheckBills;
    private Integer checkBillUserId;
    private String checkBillTime;

    private String checkBillTimeBg;
    private String checkBillTimeEd;
    private String limitDateBg;  //进入万序开始时间
    private String limitDateEd;   //进入万序结束时间


    private String memberRealName;
    private String memberLoginName;
    private String memberRankName;
    private String auditUserName;
    private String stateName;
    private String commitTimeBg;
    private String commitTimeEd;
    private String paymentTypeNo;
    private String ids;
    private String memberMobile;

    private String paymentTypeLogo;
    private String urlFromNo;
    private String isEMS;
    private String channelNo;

    private String[] paymentModeArray;
    private String tempPrhase;

    private List orderLogList;

    private BigDecimal skuAmount;
    private String allUsedPromoteRuleIds;
    private BigDecimal tempOrderFee;
    private List<GoodsGift> orderGiftList;
    private List<OrderItem> orderItems;

    private String returnType;
    private String returnValue;

    private String paymentTypeName;
    private String paidTimeBg;
    private String paidTimeEd;
    private String searchOldOrder;

    private String proImageUrl;
    private String imageOrign;
    private String codeValue;
    private String codeNo;
    private String codeOrder;
    private String companyNo;
    private String companyName;

    private String commitTimeEnd;
    private String website;

    private String countId;
    private String countFee;

    private Integer couponTypeUsId;
    private String couponNo;

    private String provinceName;
    private String cityName;
    private String areaName;

    private String isUpload;
    private Integer uploadUserId;
    private String uploadTime;
    private String promoteRuleIds;
    private String goodsBatchNo;
    private String goodsNo;

    private String isTc;
    /**优惠券批次号*/
    private Long publishId;
    /**用户状态*/
    private String userState;

    private Long stockId;//仓库id（海外购订单使用）
    private String stockNo;//仓库编号（海外购订单使用）
    private String stockName;//仓库名（海外购2期订单使用）

    //----下面是非数据库字段
    private String producerName;//生产厂家名称
    private String goodsName;//商品名称
    private String paymentType;//支付方式
    private String loginName;//登入名
    private String discountCode;//优惠劵码
    private String discountType;//优惠方式
    private BigDecimal goodsAmount;//商品数量
    private BigDecimal goodsPrice;//商品价格
    private String orderStateStr;//订单状态字符型
    private Long produceId;//
    private Long goodsId;
    private BigDecimal oneGoodsSumPrice;//销售金额=商品数量*商品金额
    private String wi;//cps 标志
    private String cid;//cps 标志
    private String ispaidStr;//支付状态
    //订单汇总
    private BigDecimal orderPointsMoney; //积分总金额
    private BigDecimal orderVouchersMoney; //优惠券总金额
    private BigDecimal returnGoods;   //退款总金额
    private BigDecimal totalNum;  //总订单数
    private BigDecimal totalMoney;  //订单总金额
    private BigDecimal aveNum;  //平均客单数
    private BigDecimal avegoods;  //平均商品件数

    //第一次下单
    private BigDecimal orderPointsMoneyFirst; //积分总金额
    private BigDecimal orderVouchersMoneyFirst; //优惠券总金额
    private BigDecimal returnGoodsFirst;  //退款总金额
    private BigDecimal totalNumFirst;  //总订单数
    private BigDecimal totalMoneyFirst;  //订单总金额
    private BigDecimal aveNumFirst;  //平均客单数
    private BigDecimal avegoodsFirst;  //平均商品件数
    private BigDecimal goodsPriceFirst; //商品总成本

    private Integer userId;  //查看人ID
    private String type; //是不是my

    private String memberType; //客户类型
    private String buyGoods;	//订单商品
    private String goodsNos;	//订单商品编号
    private String catalogId;	//商品类别
    private String catalogName;
    private String sale;

    private String workGoodsCostPrice;	//商品成本
    private String userRealName;	//销售员姓名
    private String userName;	//销售员账号
    private String[] goodSkus;	//商品sku编码集合
    private Integer groupId;		//销售人员分组ID
    private String isReturn;	//是否退货
    private String isComplete;	//是否完成
    private String isAddWork;	//是否已计入销售业绩
    private BigDecimal goodsSumFee;

    private String discountPlan;	//促销方案
    private String promoteRuleName;	//促销规则名称
    private String promoteRuleNo;	//促销规则编号

    private String userIdChannel; //获取渠道权限的用户ID
    private List<Integer> bizIds = new ArrayList<Integer>();
    //来源渠道的集合
    private List<BigDecimal> mults = new ArrayList<BigDecimal>();
    //运维Id的集合
    private List<ProductYwCatalog> ywIds = new ArrayList<ProductYwCatalog>();
    private List<ProductYwCatalog> ywIds1 = new ArrayList<ProductYwCatalog>();
    private List<ProductYwCatalog> inListTot = new ArrayList<ProductYwCatalog>();

    private String refundOrderNotes;//退款流水号
    private String cancelTimeBg;
    private String cancelTimeEd;

    //肾宝片导出
    private String page;  //单位
    private String daochu;  //导出状态
    private String wuliuName; //物流公司
    private String fahuoTime; //发货时间

    /**
     * 是否已评价
     */
    private String iseval;
    //是否参与虎彩网抽奖
    private String isHcwCj;
    //计算免邮的价钱 =商品总价-优惠金额-优惠卷抵扣金额
    private BigDecimal myFee;
    //是否第一次下单
    private String orderNew;
    //订单号集合
    private String[] orderIds;
    //会员识别码
    private String memberKey;
    //来源
    private String mul;
    /*
     * 识别码
     */
    private String contentNo;
    /*
     * 商品图片集合
     */
    private List<String> goodsImageList;
    /*
     * 订单可操作状态集合
     */
    private List<String> orderButs;
    /*
     * 操作人名称
     */
    private String addUserName;
    /*
     * 会员收货地址ID
     */
    private Long memberAddressId;
    /*
      * 订单返还积分
      */
    private Integer returnPoint;
    /*
     * 订单商品列表
     */
    private List<Goods> goodsList;
    /*
     * 购物车是否合并
     */
    private String isMerger;

    //健一卡账户余额
    private BigDecimal accountFee;
    //健一卡账户是否设置密码Y/N
    private String isSetPass;
    //支付宝或微信支付对应的加密串
    private String securityKey;
    /**
     * 处方单图片列表
     */
    private List<String>  prescriptionImgs;

    //是否上传处方单成功
    private String isUploadPrescription;

    private  String  packageStand;


    private String isNewMuliPkg;
    private	String weixinOrderNo;

    /***
     * 审方状态(指订单为处方药时，审核处方单的状态):  1:待审核  2:审核通过  3:审核未通过
     */
    private Integer prescriptionCheckingState;
}
