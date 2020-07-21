package com.j1.action;

/**
 * Created by wangchuanfu on 20/7/20.
 */
public class WebSearchBaseAction {
    /**
     * 页码转换
     *
     * @param pageNum
     * @return
     */
    protected Integer parsePageNum(String pageNum) {
        try {
            return Integer.parseInt(pageNum);
        } catch (Exception e) {

        }
        return 1;
    }

}
