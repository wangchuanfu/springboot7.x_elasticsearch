package com.j1.xiaoxiang.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ShoesTradeGoodsPropertyEnum {
    /**
     * 属性名编码(1：货号，2：发售价格，3：发售日期，4：功能性，5：适用季节，6：配色，7：闭合方式，8：鞋头款式，9：鞋帮高度，
     * 10：鞋底材料，11：鞋跟类型，12：鞋面材质，13：风格，14：适用人群)',
     */
    HUO_HAO(1, "货号"),
    FA_SHOU_JIA_GE(2, "发售价格"),
    FA_SHOU_RI_QI(3, "发售日期"),
    GONG_NENG_XING(4, "功能性"),
    SHI_YONG_JI_GE(5, "适用季节"),
    PEI_SE(6, "配色"),
    BI_HE_FANG_SHI(7, "闭合方式"),
    XIE_TOU_KUAN_SHI(8, "鞋头款式"),
    XIE_BANG_GAO_DU(9, "鞋帮高度"),
    XIE_DI_CAI_LIAO(10, "鞋底材料"),
    XIE_GEN_LEI_XING(11, "鞋跟类型"),
    XIE_MIAN_CAI_ZHI(12, "鞋面材质"),
    FENG_GE(13, "风格"),
    SHI_YONG_REN_QUN(14, "适用人群"),
    CHI_CUN(100, "尺码"),
    ;
    private int propertyCode;
    private String propertyName;
}
