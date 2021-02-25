package com.j1.common.base;

import java.util.List;
import java.util.Map;

/**
 * Created by wangchuanfu on 20/7/20.
 */
public class FacetsResult {

    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 313199085507254257L;

    private int totalHits;
    private List<Map<String, Object>> results;

    public void install(List<Map<String, Object>> result, int totalHits) {
        this.results = result;
        this.totalHits = totalHits;
    }

    public int getTotalHits() {
        if (totalHits < 0)
            totalHits = 0;
        return totalHits;
    }

    public void setTotalHits(int totalHits) {
        this.totalHits = totalHits;
    }

    public List<Map<String, Object>> getResults() {
        return results;
    }

    public void setResults(List<Map<String, Object>> results) {
        this.results = results;
    }
}
