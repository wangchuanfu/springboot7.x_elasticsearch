package com.j1.pojo;

import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * 商品表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_gm_goods")
public class TGmGoods {
    /**
     * 主键id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商品编号
     */
    @Column(name = "goods_code")
    private String goodsCode;

    /**
     * 商品条码
     */
    @Column(name = "goods_bar_code")
    private String goodsBarCode;

    /**
     * 名称
     */
    @Column(name = "goods_name")
    private String goodsName;

    /**
     * 商品视频url
     */
    @Column(name = "video_url")
    private String videoUrl;

    /**
     * 首页展示图片URL
     */
    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    /**
     * 库存
     */
    @Column(name = "repertory")
    private Integer repertory;

    /**
     * 分类id
     */
    @Column(name = "category_id")
    private Integer categoryId;

    /**
     * 系列id
     */
    @Column(name = "series_id")
    private Integer seriesId;

    /**
     * 品牌id
     */
    @Column(name = "brand_id")
    private Integer brandId;

    /**
     * 海淘商品标记，1 是， -1 否
     */
    @Column(name = "haitao_flag")
    private Integer haitaoFlag;

    /**
     * 货号
     */
    @Column(name = "goods_number")
    private String goodsNumber;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 点击量
     */
    @Column(name = "click_count")
    private String clickCount;

    /**
     * 销量
     */
    @Column(name = "sale_count")
    private Integer saleCount;

    /**
     * 最新上架时间
     */
    @Column(name = "put_away_time")
    private Date putAwayTime;

    /**
     * 0:未销售 1:销售中
     */
    @Column(name = "`status`")
    private Integer status;

    /**
     * 是否删除(0:否 1:是)
     */
    @Column(name = "delete_flag")
    private Boolean deleteFlag;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "ext1")
    private Integer ext1;

    @Column(name = "ext2")
    private String ext2;

    @Column(name = "ext3")
    private String ext3;
}