package com.j1.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by wangchuanfu on 20/8/10.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsActivityLabel extends BaseBO {

    /**
     * 标签ID
     */
    private Integer lableId;
    /**
     * 标签名称
     */
    private String lableName;
    /**
     * 标签图片地址
     */
    private String lableImgUrl;
    /**
     * 标签活动URL
     */
    private String lableUrl;
    /**
     * 描述
     */
    private String lableNotes;
}
