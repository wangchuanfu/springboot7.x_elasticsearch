package com.j1.pojo.pojo;

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
public class EcPromoteRuleGoodsTNew {

    private Integer pcgTypingGoodsId;
    private Long promoteRuleId;
    private Long promoteConditionGoodsId;
    private Long ruleItemId;
    private Long goodsId;
    private String typingPrice;

    private String goodsNo;
    private String goodsName;
    private String ecPrice;

    private String type;
    private String pcgTypingGoodsIds;

    private String unitName; // 单位
    private String marketPrice; // 市场价
    private String availableStock; // 库存

    private String isTrue; // 是否送出

    private Long productId; // 商品ID
    private String proImageUrl; // 图片地址
    private String packageStandard;
    private String producer;
    private Long promoteGoodsId; // 促销商品ID
    private Integer stockId; // 仓库ID
    private BigDecimal hgCount; // 最大换购数
}
