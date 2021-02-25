package com.j1.type;

/**
 * Created by wangchuanfu on 20/7/16.
 */
public enum NewProductFieldEnum {

    _id("ID"),
    //显示字段
    proCatalogId("分类ID"),
    proCatalogName("分类名称"),
    promotePhrase("促销信息"),
    ecPrice("售价"),
    marketPrice("市场价"),
    proImageUrl("图片路径"),
    tagIconUrl("图标路径"),
    drugPrescriptionType("商品类型"),
    productLeastOrder("最小订购量"),
    orderLimitAmount("最大订购量"),
    availableStock("可用库存"),
    productId("产品ID"),
    goodsId("商品ID"),
    saleScore("销售量权重"),
    saleAmount("总销量"),
    goodsNo("商品编码"),
    commentSum("评论条数"),
    attrs("属性"),
    cataLogListId("所属目录Id列表"),
    cataLogListName("所属目录名字列表"),
    //索引字段
    productName("产品名称"),
    productName1("产品名称"),
    productChnNo("拼音码"),
    drugTreatment("主治功能"),
    productKeyword("关键字"),
    productBrandName("品牌"),
    productBrandId("品牌Id"),
    productBrandName1("品牌"),
    productOrder("排名"),
    discount("促销"),
    stock("是否有库存"),
    saleTime("上架时间"),
    clickAmount("点击总量"),
    goodEvalAmount("好评总量"),
    cataLogPathId("目录索引"),
    productCommonName("商品通用名"),
    promoteIds("高品促销ids"),

    //ES分组时的字段名
    term("字段"),
    count("数量"),
    esTag("分类标签"),

    //soa显示字段
    //分类
    catalogId("分类ID"),
    catalogName("分类名称"),
    defaultCatalogName("其它分类"),
    //属性
    attrName("属性大类名"),
    attrCode("属性大类编码"),
    attrSubName("子属性名"),
    attrSubCode("子属性编码");

    private String i;

    private NewProductFieldEnum(String n) {
        this.i = n;
    }

    public String getCode() {
        return this.i;
    }

}
