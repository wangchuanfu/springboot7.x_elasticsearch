package com.j1.test;

import java.math.BigDecimal;

/**
 * Created by wangchuanfu on 20/9/16.
 */
public class ShopcartGoods {


    private String shopcartGoodsType;//海外商品还是j1商品
    /*
         * ID
         */
    private Long shopcartGoodsId;
    /*
     * 识别码
     */
    private String contentNo;
    /*
     * 购物车商品信息   商品ID,商品数量,多规格ID,渠道ID,是否选中
     */
    private String skuContent;
    /*
     * 会员ID
     */
    private Long memberId;
    /*
     * 来源web/wap/app
     */
    private String mul;
    /*
     * 商品ID
     */
    private Long goodsId;
    /*
     * 多规格ID
     */
    private Long skuId;
    /*
     * 渠道ID
     */
    private Integer multiId;
    /*
     * 数量，数量为空表示不修改数量
     */
    private BigDecimal amount;
    /*
     * add加/sub减/update修改
     */
    private String type;
    /*
     * 是否选中Y/N
     */
    private String isSelected;
    /*
     * 促销类型，按促销条件"ruleId""或按商品"goodsId"
     */
    private String promoteType;
    /*
     * 对应的促销ID或商品ID
     */
    private Long promoteId;
    /*
     * 促销的itemId
     */
    private Long promoteItemId;
    /*
     * 促销选中的商品集合，格式：goodsId,goodsId,goodsId
     */
    private String goodsIds;

    private String standby1;
    private String standby2;
    private String standby3;
    private String standby4;
    private String standby5;
    //会员识别码
    private String memberKey;

    //是否合并(不合并只取识别码中的信息,Y/N)
    private String isMerger;

    // 支付卡黑名单提示(多个用、隔开)
    private String blackPaymentPrompt;



    public String getBlackPaymentPrompt() {
        return blackPaymentPrompt;
    }

    public void setBlackPaymentPrompt(String blackPaymentPrompt) {
        this.blackPaymentPrompt = blackPaymentPrompt;
    }

    public String getShopcartGoodsType() {
        return shopcartGoodsType;
    }

    public void setShopcartGoodsType(String shopcartGoodsType) {
        this.shopcartGoodsType = shopcartGoodsType;
    }

    public String getIsMerger() {
        return isMerger;
    }

    public void setIsMerger(String isMerger) {
        this.isMerger = isMerger;
    }

    public String getSkuContent() {
        return skuContent;
    }

    public void setSkuContent(String skuContent) {
        this.skuContent = skuContent;
    }

    public String getMemberKey() {
        return memberKey;
    }

    public void setMemberKey(String memberKey) {
        this.memberKey = memberKey;
    }

    public Long getShopcartGoodsId() {
        return shopcartGoodsId;
    }

    public void setShopcartGoodsId(Long shopcartGoodsId) {
        this.shopcartGoodsId = shopcartGoodsId;
    }

    public String getStandby1() {
        return standby1;
    }

    public void setStandby1(String standby1) {
        this.standby1 = standby1;
    }

    public String getStandby2() {
        return standby2;
    }

    public void setStandby2(String standby2) {
        this.standby2 = standby2;
    }

    public String getStandby3() {
        return standby3;
    }

    public void setStandby3(String standby3) {
        this.standby3 = standby3;
    }

    public String getStandby4() {
        return standby4;
    }

    public void setStandby4(String standby4) {
        this.standby4 = standby4;
    }

    public String getStandby5() {
        return standby5;
    }

    public void setStandby5(String standby5) {
        this.standby5 = standby5;
    }

    public String getGoodsIds() {
        return goodsIds;
    }

    public void setGoodsIds(String goodsIds) {
        this.goodsIds = goodsIds;
    }

    public String getContentNo() {
        return contentNo;
    }

    public void setContentNo(String contentNo) {
        this.contentNo = contentNo;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMul() {
        return mul;
    }

    public void setMul(String mul) {
        this.mul = mul;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Integer getMultiId() {
        return multiId;
    }

    public void setMultiId(Integer multiId) {
        this.multiId = multiId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(String isSelected) {
        this.isSelected = isSelected;
    }

    public String getPromoteType() {
        return promoteType;
    }

    public void setPromoteType(String promoteType) {
        this.promoteType = promoteType;
    }

    public Long getPromoteId() {
        return promoteId;
    }

    public void setPromoteId(Long promoteId) {
        this.promoteId = promoteId;
    }

    public Long getPromoteItemId() {
        return promoteItemId;
    }

    public void setPromoteItemId(Long promoteItemId) {
        this.promoteItemId = promoteItemId;
    }
}
