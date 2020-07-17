package com.j1.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by wangchuanfu on 20/7/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseBO {

    private Integer productId;
    private String productCode;
    private String productName;
    private String productCommonName;
    private String productAliasName;
    private String productChnNo;
    private Integer productOrder;
    private Integer proCatalogId;
    private Integer productBrandId;
    private Integer drugApproveType;
    private String drugApproveNo;
    private String drugTreatment;
    private String drugComposition;
    private Integer drugType;
    private String drugPrescriptionType;
    private String productLocality;
    private String producer;
    private String qualityLimit;
    private String productMarketDate;
    private Integer productTypeId;
    private String productKeyword;
    private String productDesc;
    private Integer unitId;
    private String isOnsale;
    private String onsaleTime;
    private BigDecimal productLeastOrder;
    private BigDecimal orderLimitAmount;
    private String isStockWarn;
    private Integer productPoint;
    private String isSupportPoint;
    private BigDecimal productWarnNumber;
    private String catalogCode;
    private String seoTitle;
    private String seoMetaKeyWord;
    private String seoMetaDesc;
    private String detailInfo;
    private String deliveryNotes;
    private String privateSecurity;
    private String saleService;
    private String honors;
    private String proCatalogName;
    private String productNum;
    private String productDetailInfo;
    /**
     * 延伸字段
     */
    private String unitName;


}