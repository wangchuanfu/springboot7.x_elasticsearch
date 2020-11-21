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
 * Created by wangchuanfu on 20/8/13.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "member_info_index", type = "#{esAttribute.indexType}", shards = 5, replicas = 1)
public class MemberVo extends BaseBO {
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private Integer pointChange;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String backURL;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String loginName;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String nickName;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String loginPassword;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String loginPassword2;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String sex;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String realName;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String birthday;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String identifyCard;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String isMarried;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String mobile;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String email;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String emailState;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private Integer provinceId;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private Integer cityId;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private Integer areaId;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String provinceName;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String cityName;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String areaName;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String keyword;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String keyword2;
    @Field(type = FieldType.Keyword ,index =true ,store = false)


    private Long memberId;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private Integer memberRankId;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String[] memberIds;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String memberIdsS;
    @Field(type = FieldType.Keyword ,index =true ,store = false)


    private String address;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String fullAddress;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String regTime;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String regType;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String memberTagIds;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String memberTagNames;
    @Field(type = FieldType.Keyword ,index =true ,store = false)


    private BigDecimal accountFee;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private Integer currentPoints;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private Integer currentPointsBg;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private Integer currentPointsEd;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private Integer sumPoints;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private Integer sumOrders;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private Integer sumOrdersBg;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private Integer sumOrdersEd;

    /**
     * 储值卡交易密码
     * 2012-06-14
     * guozhifeng
     **/
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String tradePassword;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private BigDecimal sumFees;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private Integer sumOnlineTime;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String lastOrderTime;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String memberState;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String notes;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String isDelete;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String addTime;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private Integer addUserId;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String editTime;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private Integer editUserId;
    @Field(type = FieldType.Keyword ,index =true ,store = false)


    private String regTimeBg;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String regTimeEd;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String sumFeesBg;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String sumFeesEd;
    @Field(type = FieldType.Keyword ,index =true ,store = false)


    private String memberLogo;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String tel;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String mobileState;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String mobileActiveCode;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String mobileActiveTime;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String getPwdCode;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String getPwdTime;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String emailActiveTime;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String emailActiveCode;
    @Field(type = FieldType.Keyword ,index =true ,store = false)




    private BigDecimal orderFee;	//消费总额
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private Integer orderCount;	//总消费次数
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private BigDecimal avgFees;	//平均消费金额
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private BigDecimal goodsCastPrice;	//商品成本
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private BigDecimal goodsProfit;	//利润
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String profitRate;	//毛利率
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String workState;	//是否已经分配给呼叫中心 not表示未分配
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String workCount;
    private Integer workCountBg;	//会员分过的次数
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private Integer workCountEd;	//会员分过的次数
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String workByUserName;	//当前所属客服账号
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String workByUserRealName;	//当前所属客服名称
    @Field(type = FieldType.Keyword ,index =true ,store = false)




    //客户详情部分添加字段
    private String isOld; //是否老客户
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String sale;  //所属销售
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String multName;  //客户来源
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String isValid;  //是否有效
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String memberBuyGoodsNo;	//会员买过的商品的编码
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String[] memberBuyGoodsNos;	//会员买过的商品的编码集合
    private String orderTimeBg;	//订单开始时间
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String orderTimeEd;	//订单结束时间
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String isvalid;		//订单是否有效
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String isEmail;	//账号名是否邮箱
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private BigDecimal rankLevel;	//会员级别
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String memberRankTime;	//会员升级时间
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String memberRankTimeBg;	//会员升级开始时间
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String memberRankTimeEd;	//会员升级结束时间
    @Field(type = FieldType.Keyword ,index =true ,store = false)


    private Long [] tagLibraryIds;//会员标签id集合
    @Field(type = FieldType.Keyword ,index =true ,store = false)


    private Integer strLength;  //用*替换的长度
    @Field(type = FieldType.Keyword ,index =true ,store = false)


    private String auditState;  //审核状态
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String paymentLimit;//支付类型限制
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private Integer orderSum;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private Integer hdfkSum;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private Integer kdfhSum;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String opBlackUser;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String blackNote;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private Integer opBlackUserId;
    @Field(type = FieldType.Keyword ,index =true ,store = false)


    private String qBeginDate;//优惠券开始有效期
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String qEndDate;//优惠券结束有效期
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private Integer useType;//优惠券类型
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String activityNo;//促销活动标识
    @Field(type = FieldType.Keyword ,index =true ,store = false)



    private String isSendMsg;
    @Field(type = FieldType.Keyword ,index =true ,store = false)

    private String msgContent;
}
