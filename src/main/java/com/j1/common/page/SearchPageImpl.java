package com.j1.common.page;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.*;

/**
 * Created by wangchuanfu on 20/7/16.
 */
public class SearchPageImpl<T> {

    private final SearchPageRequest pageRequest;
    private final List<T> data = new ArrayList<T>();

    private String requestURL;// 请求URL
    private String params;// chen_zhenyu添加的，用于传递查询的参数
    private String extraInfo;// 请求地址的扩展信息，例如?keyword=ABC]
    private List<Map<String, Object>> attrList;

    public SearchPageImpl(List<T> content, SearchPageRequest pageRequest) {
        if (content != null) {
            this.data.addAll(content);
        }
        this.pageRequest = pageRequest;
    }

    public SearchPageImpl(List<T> content) {

        this(content, null);
    }

    /**
     * 总页数
     */
    public int getTotalPage() {

        return getPsize() == 0 ? 0 : (int) Math.ceil((double) pageRequest.getTotal() / (double) getPsize());
    }

    /**
     * 内容列表的元素总数
     */
    @JSONField(serialize = false)
    public int getNumberOfElements() {

        return data.size();
    }

    /**
     * 总内容数
     */
    public long getTotalCount() {

        return pageRequest.getTotal();
    }

    /**
     * 是否有上一页
     */
    public boolean hasPreviousPage() {

        return getPnum() > 0;
    }

    /**
     * 是否是第一页
     */
    public boolean isFirstPage() {
        return !hasPreviousPage();
    }

    /**
     * 是否有下一页
     */
    public boolean hasNextPage() {
        return ((getPnum() + 1) * getPsize()) < pageRequest.getTotal();
    }

    /**
     * 是否为最后一页
     */
    public boolean isLastPage() {
        return !hasNextPage();
    }

    /**
     * 遍历内容
     */
    public Iterator<T> iterator() {
        return data.iterator();
    }

    /**
     * 内容列表是否为空
     */
    public boolean hasData() {
        return !data.isEmpty();
    }

    /**
     * 打印方法重写
     */
    @Override
    public String toString() {
        String contentType = "UNKNOWN";

        if (data.size() > 0) {
            contentType = data.get(0).getClass().getName();
        }

        return String.format("Page %s of %d containing %s instances", getPnum(), getTotalPage(), contentType);
    }

    /**
     * 对比方法重写
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof SearchPageImpl<?>)) {
            return false;
        }

        SearchPageImpl<?> that = (SearchPageImpl<?>) obj;

        boolean totalEqual = this.pageRequest.getTotal() == that.pageRequest.getTotal();
        boolean contentEqual = this.data.equals(that.data);
        boolean pageRequestEqual = this.pageRequest == null ? that.pageRequest == null : this.pageRequest.equals(that.pageRequest);

        return totalEqual && contentEqual && pageRequestEqual;
    }

    /**
     * 哈希地址计算重写
     */
    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + (int) (pageRequest.getTotal() ^ pageRequest.getTotal() >>> 32);
        result = 31 * result + (pageRequest == null ? 0 : pageRequest.hashCode());
        result = 31 * result + data.hashCode();

        return result;
    }

    public String getRequestURL() {
        return requestURL;
    }

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @JSONField(serialize = false)
    public SearchPageRequest getPageRequest() {
        return pageRequest;
    }

    public long getTotal() {
        return pageRequest.getTotal();
    }

    public boolean isDiscount() {
        return pageRequest.isDiscount();
    }

    @JSONField(serialize = false)
    public String[] getKeywordSeacher() {
        return pageRequest.getKeywordSeacher();
    }

    public String getKey() {
        return pageRequest.getKey();
    }

    public String getPkey() {
        return pageRequest.getPkey();
    }

    public String getStype() {
        return pageRequest.getStype();
    }

    public String getAttrs() {
        return pageRequest.getAttrs();
    }

    public int getPsize() {
        return pageRequest == null ? 0 : pageRequest.getPsize();
    }

    public int getPnum() {
        return pageRequest == null ? 0 : ((pageRequest.getPnum() + 1) > getTotalPage() ? getTotalPage() : (pageRequest.getPnum() + 1));
    }

    public String getCid() {
        return pageRequest.getCid();
    }

    public String getSort() {
        return pageRequest.getSort();
    }

    /**
     * 页面呈现内容，返回不能修改的列表
     */
    public List<T> getData() {
        return Collections.unmodifiableList(data);
    }

    public List<Map<String, Object>> getAttrList() {
        return attrList;
    }

    public void setAttrList(List<Map<String, Object>> attrList) {
        this.attrList = attrList;
    }

}
