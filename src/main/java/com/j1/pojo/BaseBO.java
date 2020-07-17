package com.j1.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by wangchuanfu on 20/7/17.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseBO {
    private Integer addUserId;
    private String addTime;
    private Integer editUserId;
    private String editTime;
    private String isDelete;

    private Integer startRow;
    private Integer endRow;
    private String orderBy;


}
