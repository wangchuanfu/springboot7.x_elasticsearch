package com.j1.common.page;

import com.alibaba.fastjson.annotation.JSONField;
import com.j1.common.base.DefaultParams;
import com.j1.common.base.Sort;
import com.j1.common.utils.Integers;
import com.j1.common.utils.ValidateUtils;
import com.j1.common.utils.WebUtils;
import com.j1.type.SearchType;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by wangchuanfu on 20/7/16.
 */
public class SearchPageRequest {
    /**
     *  页面传过来的，分页参数接口实现，从0页开始，最大页面99
     */
 //是否进行SEM查询，基于关键字的排序
    @JSONField(serialize = false)
    public boolean isSEM;
    /**
     * 关键字
     */
    private String key;
    /**
     * 解码后的关键词
     */
    private String decodeKeyword;
    /**
     * 用于查找的关键词，关键词是拼音时，数组[长度2]{关键词, 拼音}，不是拼音时，数组[长度1]{关键词}
     */
    private String[] keywordSeacher;
    /**
     * 上一次查找的关键字
     */
    private String pkey;
    /**
     * 解码后的上一次查找的关键字
     */
    private String decodePrevKeyword;
    /**
     * 上一次查找的结果,目前不使用词库，不进行整词查找（用于程序里执行）
     */
    private SearchType searchType;
    /**
     * 属性
     */
    private String attrs;
    /**
     * 每页显示数
     */
    private int psize = 28;
    /**
     * 页码
     */
    private int pnum = 0;
    /**
     * 分类
     */
    private String cid;
    /**
     * 排序
     */
    private String sort;
    /**
     * 是否促销商品
     */
    private boolean discount;
    /**
     * 商品总数
     */
    private long total;
    public SearchPageRequest(String key, String prevKeyword, String stype,
                             String attrs, String pageSize, String pageNum, String catalogId,
                             String sort) throws Exception {
        // 1. 关键词解码处理
        if (key == null || "".equals(key.trim())) {
            if (!ValidateUtils.isProCatalogIdAvailable(catalogId)) {
                this.key = "%E6%AC%A7%E5%A7%86%E9%BE%99%E8%A1%80%E5%8E%8B%E8%AE%A1";
                this.decodeKeyword = "欧姆龙血压计";
            }
        } else {
            this.key = key;
            this.decodeKeyword = WebUtils.convertUnicode(this.key.trim());
        }
        this.pkey = prevKeyword;
        this.decodePrevKeyword = WebUtils.convertUnicode(this.pkey);
        if ((this.decodePrevKeyword == null
                || !this.decodeKeyword.equals(this.decodePrevKeyword.trim())) && stype == null) {
            this.searchType = SearchType.SEGMENT_C_AND;
            this.isSEM = true;
        } else {
            String tstype = null;
            if (stype != null && stype.startsWith("-")) {
                this.isSEM = false;
                tstype = stype.substring(1);
            } else {
                this.isSEM = true;
                tstype = stype;
            }
            this.searchType = SearchType.codeOf(tstype);
        }
        // 2. 属性
        if (attrs == null && ValidateUtils.isProCatalogIdAvailable(catalogId))
            this.attrs = "1";
        else
            this.attrs = attrs;
        // 3.1 每页显示条数
        int tpsize = Integers.parseInteger(pageSize, 28);
        // 3.2. 4个商品一排，最多显示24排，最大96个
        this.psize = (tpsize <= 0 || tpsize > 96) ? 28 : tpsize;
        // 4. 页码
        int tpnum = Integers.parseInteger(pageNum, 0);
        this.pnum = tpnum - 1 < 0 ? 0 : tpnum - 1;
        // 5. 分类id
        if (catalogId == null)
            this.cid = "0";
        else
            this.cid = catalogId;
        // 6. 排序
        if (sort == null)
            this.sort = "0";
        else
            this.sort = sort;
        this.keywordSeacher = new String[]{key};
    }

    public SearchPageRequest(String key, String prevKeyword, String stype,
                             String attrs, String catalogId) throws Exception {
        this(key, prevKeyword, stype, attrs, "1", "1", catalogId, "0");
        this.keywordSeacher = new String[]{key};
    }

    @JSONField(serialize = false)
    public void checkPageNumberOutBound(Long total) {
        int tPageNumInt = (int) Math.ceil(total / psize);
        this.pnum = (pnum > tPageNumInt ? tPageNumInt : pnum);
        this.total = total;
    }

    @JSONField(serialize = false)
    public boolean checkSearchType(SearchType searchType) {
        if (searchType.equals(this.searchType))
            return true;
        else
            return false;
    }

    @JSONField(serialize = false)
    public SearchType getSearchType() {
        return searchType;
    }

    @JSONField(serialize = false)
    public void setSearchType(SearchType searchType) {
        this.searchType = searchType;
    }

    @JSONField(serialize = false)
    public String getDecodeKeyword() {
        return decodeKeyword;
    }

    @JSONField(serialize = false)
    public void setDecodeKeyword(String decodeKeyword) {
        this.decodeKeyword = decodeKeyword;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public String[] getKeywordSeacher() {
        return keywordSeacher;
    }

    public void setKeywordSeacher(String[] keywordSeacher) {
        this.keywordSeacher = keywordSeacher;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPkey() {
        return pkey;
    }

    public void setPkey(String pkey) {
        this.pkey = pkey;
    }

    public String getStype() {
        return searchType.getCode();
    }

    public void setStype(String stype) {
        this.searchType = SearchType.codeOf(stype);
    }

    public String getAttrs() {
        return attrs;
    }

    public void setAttrs(String attrs) {
        this.attrs = attrs;
    }

    public int getPsize() {
        return psize;
    }

    public void setPsize(int psize) {
        this.psize = psize;
    }

    public int getPnum() {
        return pnum;
    }

    public void setPnum(int pnum) {
        this.pnum = pnum;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @JSONField(serialize = false)
    public Sort getSortObj() {
        Sort sort0 = null;
        Sort.Order orderkeyword = null;
        if (StringUtils.isNotBlank(this.decodeKeyword))
            orderkeyword = new Sort.Order(Sort.Direction.DESC, this.decodeKeyword);
        Sort.Order order99 = new Sort.Order(Sort.Direction.DESC,
                DefaultParams.DEFAULT_ORDER_BY);
        if (isSEM && (SearchType.SEGMENT_C_AND.equals(searchType)
                || SearchType.SEGMENT_C_OR.equals(searchType))
                && orderkeyword !=null) {
            if ("0".equals(sort)) {
                sort0 = new Sort("98");
                //按照关键字排序,search DSL 语句报错,先注释掉,以观后效
                // sort0.add(orderkeyword);
                sort0.add(order99);
            } else {
                sort0 = new Sort("98", sort);
                // sort0.add(orderkeyword);
                sort0.add(order99);
            }
        } else {
            if ("0".equals(sort)) {
                sort0 = new Sort("98",sort);
                sort0.add(order99);
            } else {
                sort0 = new Sort("98",sort,"1");
                sort0.add(order99);
            }
        }
        return sort0;
    }

    // ==================================================================

    public SearchPageRequest() {

    }

    /**
     * 偏移值=每页显示内容数*当前页数
     */
    @JSONField(serialize = false)
    public int getOffset() {
        return pnum * psize;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof SearchPageRequest)) {
            return false;
        }

        SearchPageRequest that = (SearchPageRequest) obj;

        boolean pEqual = this.pnum == that.pnum;
        boolean sEqual = this.psize == that.psize;

        boolean sortEqual = this.sort == null ? that.sort == null : this.sort
                .equals(that.sort);

        return pEqual && sEqual && sortEqual;
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + pnum;
        result = 31 * result + psize;
        result = 31 * result + (null == sort ? 0 : sort.hashCode());

        return result;
    }

    @Override
    public String toString() {
        return "key=" + key + ", " + "decodeKeyword=" + decodeKeyword + ", "
                + "keywordSeacher[]=" + keywordSeacher + ", " + "pkey=" + pkey
                + ", " + "decodePrevKeyword=" + decodePrevKeyword + ", "
                + "searchType=" + searchType + ", " + "attrs=" + attrs + ", "
                + "psize=" + psize + ", " + "pnum=" + pnum + ", " + "cid="
                + cid + ", " + "sort=" + sort + ", " + "discount=" + discount
                + ", " + "total=" + total + "";
    }

    public static void main(String[] args) throws Exception {
        System.out.println("-".substring(1));

        // SearchPageRequest pr = new SearchPageRequest("key", "pkey", "1",
        // "2_11", "1", "20", "123", "3");
        // String json = JSON.toJSONString(pr);
        // System.out.println(json);
        // SearchPageRequest pr2 = JSON.parseObject(json,
        // SearchPageRequest.class);
        // System.out.println(JSON.toJSONString(pr2));
    }
}
