package com.j1.xiaoxiang.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 搜索接口返给前段所需字段
 */
public class ShoesTradeResultDto {

    /**
     * id
     */
    private Integer id;
    /**
     * 对接方自定义商品标题
     */
    private String goodsName;

    /**
     * 商品编号
     */
    private String goodsCode;

    /**
     * 实际售价
     */
    private Integer sellPrice;
    /**
     * 首页展示图片URL
     */
    private String thumbnailUrl;
    /**
     * 货号
     */
    private String goodsNumber;

    /**
     * 总销量 付款人数
     */
    private Integer saleCount;
    /**
     * app跳转详情的url
     */
    private String gotoUrl;
}
