package com.j1.common.base;

/**
 * Created by wangchuanfu on 20/7/20.
 */
public interface Pageable {

    /**
     * 当前页
     * @return
     */
    int getPageNumber();

    /**
     * 每页显示的内容数
     * @return
     */
    int getPageSize();

    /**
     * 当前页 * 每页显示的内容数 = 偏移值
     * @return
     */
    int getOffset();

    /**
     * 排序
     * @return
     */
    Sort getSort();

}
