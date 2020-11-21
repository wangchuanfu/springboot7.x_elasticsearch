package com.j1.pojo.pojo;

import com.j1.pojo.BaseBO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by wangchuanfu on 20/7/29.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductAttrs extends BaseBO {

    // 属性ID
    private String attrId;

    // 属性名称
    private String attrCode ;
}
