package com.j1.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by wangchuanfu on 20/7/28.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductAttrs extends BaseBO{


    // 属性ID
    private String attrId;

    // 属性名称
    private String attrCode ;

}
