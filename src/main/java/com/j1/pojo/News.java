package com.j1.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by wangchuanfu on 20/7/15.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {
    private Long id;
    private String title;
    private String content;
    private String url;
    private String source;
    private Integer reply;
    private String postdate;


}
