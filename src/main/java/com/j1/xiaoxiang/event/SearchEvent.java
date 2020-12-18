package com.j1.xiaoxiang.event;

import com.j1.xiaoxiang.entity.dto.CommonRequest;
import lombok.Data;

@Data
public class SearchEvent  extends CommonRequest {
    //用户ID
    private String userId;
    //设备识别号
    private String deviceNo;
    //搜索词
    private String sk;
    //搜索词ID
    private Integer skId;
    //1为搜索到的，2为无结果的
    private Integer type;
}
