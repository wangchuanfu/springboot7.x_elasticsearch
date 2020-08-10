package com.j1.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangchuanfu on 20/8/10.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductGroupSku extends BaseBO {
    // ID
    private Long psgId;

    // 主商品ID
    private Long goodsId;

    // 主商品名称
    private String goodsName;

    // 主商品编码
    private String goodsNo;

    // 被关联商品数量
    private Integer goodsAmount;

    // 被关联商品单价
    private BigDecimal goodsPrice;

    // 被关联商品分组名称
    private String goodsGroupName;

    // 被关联商品id
    private Long tagGoodsId;

    // 被关联商品名称
    private String tagGoodsName;

    // 被关联商品编码
    private String tagGoodsNo;

    // 组合总价 单价*数量
    private BigDecimal goodsGroupPrice;
    // 排序
    private Integer orderNum;

    // ===========以下是非数据库字段===========
    private Integer roleId;
    private List<BigDecimal> roleList;
}
