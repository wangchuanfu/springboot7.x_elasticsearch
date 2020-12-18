package com.j1.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_gm_goods_property")
public class TGmGoodsProperty {

    /**
     * 主键
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
     * 属性名编码(1：货号，2：发售价格，3：发售日期，4：功能性，5：适用季节，6：配色，7：闭合方式，8：鞋头款式，9：鞋帮高度，10：鞋底材料，11：鞋跟类型，12：鞋面材质，13：风格，14：适用人群)
     */
    @Column(name = "property_code")
    private Integer propertyCode;

    /**
     * 属性名
     */
    @Column(name = "property_name")
    private String propertyName;

    /**
     * 属性值(多个||分隔)
     */
    @Column(name = "property_value")
    private String propertyValue;

    /**
     * 类型(1:商品属性 2:SKU属性)
     */
    @Column(name = "`type`")
    private Integer type;

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
}
