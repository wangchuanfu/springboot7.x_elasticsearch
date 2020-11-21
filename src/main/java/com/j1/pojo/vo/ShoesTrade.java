package com.j1.pojo.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "shoes_trade", type = "shoes_trade")//spring-data-es集成
public class ShoesTrade {
    /**
     * 商品id
     */
    @Id
    private Integer id;
    /**
     * 商品名称
     */
    @Field(type = FieldType.Text, analyzer = "jt_cn_max", searchAnalyzer = "jt_cn_smart")
    private String goodsName;
    /**
     *搜索查询关键字
     * sk: goodsName+productCategoryName+serisName+brandName+suitCrowdAll(元素空格分开) 全部以空格分开 后面可能要添加其他属性
     */
    @Field(type = FieldType.Text, analyzer = "jt_cn_max", searchAnalyzer = "jt_cn_smart")
    private String sk;

    /**
     * 拼音title
     */
    @Field(type = FieldType.Text, analyzer = "jt_cn_smart")
    private String skPyTitle;
    /**
     * 商品拼音标题  goodsName的拼音
     */
    @Field(type = FieldType.Text, analyzer = "jt_cn_smart")
    private String gPyTitle;
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
    @Field(type = FieldType.Integer)
    private Integer clickCount;
    /**
     *  实际售价
     */
    @Field(type = FieldType.Integer)
    private Integer sellPrice;

    /**
     *  实际售价
     */
    @Field(type = FieldType.Double)
    private Double priceScore;
    /**
     * 商品原价
     */
    @Field(type = FieldType.Integer)
    private Integer originalPrice;
    /**
     * 首页展示图片URL
     */
    private String thumbnailUrl;
    /**
     * 分类id
     */
    @Field(type = FieldType.Integer)
    private Integer productCategoryId;
    /**
     * 分类名称
     */
    private String productCategoryName;
    /**
     * 系列id
     */
    @Field(type = FieldType.Integer)
    private Integer serisId;
    /**
     * 系列名称
     */
    private String serisName;
    /**
     * 品牌id
     */
    @Field(type = FieldType.Integer)
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
     * 发售时间
     */
    private Date saleTime;
    /**
     * 总销量
     */
    private Integer saleCount;
    /**
     * 适应人群，/分开
     */
    private String suitCrowd;
    /**
     * 适应人群   @Field(type = FieldType.Integer)
     * 存储适应人群得列表 就是suitCrowd /分割得单个元素列表
     */
    @Field(type = FieldType.Keyword)
    private List<String> suitCrowdAll;
    /**
     * 尺寸，/分开
     */
    private String measures;

    /**
     * 尺寸   @Field(type = FieldType.Integer)
     * 存储尺寸得列表，就是measures 以/分开得单个元素列表
     */
    @Field(type = FieldType.Keyword)
    private List<String> measuresAll;

    /**
     {
     "key":"配色", colourMatching
     "value":"白"
     }, */
    private String colourMatching;

    /**
     {
     "key":"配色", colourMatching 列表
     "value":"白"
     }, */
    @Field(type = FieldType.Keyword)
    private List<String> colourMatchingAll;
    /**
     {
     "key":"闭合方式", closingMode
     "value":"系带"
     },*/
    private String closingMode;
    /**
     {
     "key":"鞋头款式",shoesStyle
     "value":"圆头"
     },*/
    private String shoesStyle;
    /**
     {
     "key":"鞋帮高度",shoesHeight
     "value":"高帮"
     },*/
    private String shoesHeight;
    /**
     {
     "key":"鞋底材料",shoesMaterial
     "value":"橡胶底"
     },*/
    private String shoesMaterial;
    /**
     {
     "key":"鞋跟类型",shoesType
     "value":"平跟"
     },*/
    private String shoesType;
    /**
     {
     "key":"鞋面材质",upperMaterial
     "value":"帆布"
     },*/
    private String upperMaterial;
    /**
     {
     "key":"风格",shoesFashtion 以/分开，不是以逗号分隔
     "value":"潮流,复古"
     }
     */
    private String shoesFashtion;
    /**
     {
     "key":"风格",shoesFashtion 以/分开的元素集合
     "value":"潮流,复古"
     }
     */
    @Field(type = FieldType.Keyword)
    private List<String> shoesFashtionAll;
    /**
     *是否置顶
     */
    @Field(type = FieldType.Long)
    private Long isOnTop;
}
