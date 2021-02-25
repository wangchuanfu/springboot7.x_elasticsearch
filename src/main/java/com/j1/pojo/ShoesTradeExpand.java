package com.j1.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoesTradeExpand {


    private Integer id;
    /**
     * 商品标题
     */
    private String goodsName;
    /**
     * 商品编号
     */
    private String goodsCode;
    /**
     * 商品条码
     */
    private String goodsBarCode;

    /**
     * 点击量
     */
    private Integer clickCount;
    /**
     * 实际售价
     */
    private Integer sellPrice;
    /**
     * 商品原价
     */
    private Integer originalPrice;
    /**
     * 首页展示图片URL
     */
    private String thumbnailUrl;
    /**
     * 分类id
     */
    private Integer productCategoryId;
    /**
     * 分类名称
     */
    private String productCategoryName;
    /**
     * 系列id
     */
    private Integer serisId;
    /**
     * 系列名称
     */
    private String serisName;
    /**
     * 品牌id
     */
    private Integer brandId;
    /**
     * 品牌名字
     */
    private String brandName;


    /**
     * 货号
     */
    private String goodsNumber;

    /**
     * 海淘商品标记，1 是， -1 否
     */
    private Integer haitaoFlag;
    /**
     * 第三方平台编号
     */
    private String thirdPlatformCode;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 上架时间
     */
    private Date putAwayTime;
    /**
     * 总销量
     */
    private Integer saleCount;
    /**
     * 商品属性
     */
    private List<TGmGoodsProperty> tGmGoodsProperties;
}
