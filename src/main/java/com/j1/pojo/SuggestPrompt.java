package com.j1.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by wangchuanfu on 20/8/3.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 搜索提示词model
 */
public class SuggestPrompt extends BaseBO {
    /**
     * 主键
     */
    private Integer promptId;
    /**
     * 提示词
     */
    private String promptName;
    /**
     * 搜索词
     */
    private String searchName;
    /**
     * 优先级
     */
    private Integer priority;
    /**
     * 提示次序
     */
    private Integer promptOrder;

    private String url;
}
