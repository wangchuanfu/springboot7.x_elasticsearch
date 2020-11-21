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
public class GoodsGift extends BaseBO {

    private Long giftId;
    private String giftNo;
    private String giftName;
    private String isGoods;
    private BigDecimal giftStock;
    private BigDecimal giftPrice;
    private String giftNotes;
    private BigDecimal amount;

    private String goodsId;
    private String productId;
    private String proImageUrl;
    private String packageStandard;
    private String producer;

    private Integer promoteRuleId;
    private String isTrue;

    private Integer stockId;

    private String[] exceptGiftIds;

    private String[] ids;
    /**
     * 成本价
     */
    private BigDecimal costPrice;
}
