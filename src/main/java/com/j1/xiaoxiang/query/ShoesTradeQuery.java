package com.j1.xiaoxiang.query;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 封装前台传递过来的参数 实体类
 */
public class ShoesTradeQuery {
    /**
     * 用户id,用于算法分析用户行为
     */
    @ApiModelProperty(value = "用户id",required = false)
    private String userId;
    /**
     * 设备识别号
     */
    @ApiModelProperty(value = "设备识别号",required = false)
    private String deviceNo;

    /**
     * 搜索词
     */
    @NotBlank(message = "搜索词不能为空！")
    @ApiModelProperty(value = "搜索词 字符串",required = true)
    private String sk;
    /**
     * 原始搜索词 用于备份sk，可前端可忽略，或者传sk
     */
    @ApiModelProperty(value = "原始搜索词 用于备份sk，可前端可忽略，或者传sk",required = false)
    private String originalSk;

    /**
     * 页码
     */
    @ApiModelProperty(value = "搜索页数,默认第一页",required = false)
    private Integer page = 1;
    /**
     * 每页显示的个数
     */
    @ApiModelProperty(value = "每页多少条数据,默认20条",required = false)
    private Integer pageSize = 20;
    //排序参数
    /**
     * 升序 asc
     * 降序 desc
     */
    @ApiModelProperty(value = "价格排序参数,升序 asc，降序 desc",required = false)
    private String priceSort;
    /**
     * 销量排序
     * 升序 asc
     * 降序 desc
     */
    @ApiModelProperty(value = "销量排序参数,升序 asc，降序 desc",required = false)
    private String salesVolumeSort;

    @ApiModelProperty(value = "新品时间排序参数,1代表新品排序",required = false)
    private Integer saleTimeSort=0;

    //筛选条件参数
    /**
     * 最低价
     */
    @ApiModelProperty(value = "价格排序条件，最低价格",required = false)
    private Long minPrice;
    /**
     * 最高价
     */
    @ApiModelProperty(value = "价格排序条件，最高价格",required = false)
    private Long maxPrice;
    /**
     * 品牌筛选
     */
    @ApiModelProperty(value = "品牌ids筛选条件",required = false)
    private List<String> brandIds;
    /**
     * 品牌筛选
     */
    @ApiModelProperty(value = "品牌筛选条件",required = false)
    private List<String> brands;
    /**
     * 类目ids筛选
     */
    @ApiModelProperty(value = "类目ids筛选条件",required = false)
    private List<String> categoryIds;
    /**
     * 类目筛选
     */
    @ApiModelProperty(value = "类目筛选条件",required = false)
    private List<String> categorys;
    /**
     * 系列ids筛选条件
     */
    @ApiModelProperty(value = "系列ids筛选条件",required = false)
    private List<String> serisIds;
    /**
     * 系列筛选
     */
    @ApiModelProperty(value = "系列筛选条件",required = false)
    private List<String> serisNames;

    /**
     * 人群筛选
     */
    @ApiModelProperty(value = "人群筛选条件",required = false)
    private List<String> suitCrowd;

    /**
     * 尺寸筛选
     */
    @ApiModelProperty(value = "尺寸筛选条件",required = false)
    private List<String> measures;


    /**
     * 商品来源平台，1：小象优品，2：京东（导购），21：京东（买断），3：爱淘宝，31：天猫，32：淘宝，4：苏宁,41:苏宁买断,5国美, 6亚马逊,8考拉导购,81考拉买断,82网易严选;9:云米;91小象海淘商品
     */
    @ApiModelProperty(value = "商品来源平台筛选条件",required = false)
    private List<String> platformList;

    @ApiModelProperty(value = "搜索次数",required = false)
    private Integer searchNum=1;

    @ApiModelProperty(value = "首次搜索sk",required = false)
    private String searchFirstSk;

}
