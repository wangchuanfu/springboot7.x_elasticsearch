package com.j1.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

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
    private String saleTime;
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

    private List<ProductAttrs> attrList;
    private String cataLogListId;
    private  String cataLogListName;

    private long goodEvalAmount;
    private long clickAmount;
    private String promotePhrase;
    private String ecPrice;

    private BigDecimal marketPrice;

    private String proImageUrl;
    private String tagIconUrl;
    private  BigDecimal availableStock;
    private BigDecimal saleScore;
    private  Integer saleAmount;

    private Integer goodsId;
    private String goodsNo;
    private Integer stock;
    private String thumb;
    private String productName1;
    private  String productBrandName;
    private String productBrandName1;
    private  Integer commentSum;
    private Integer cataLogPathId;
    private String discountState;
    private String goodsSeller;
    private String productAliasName;






}