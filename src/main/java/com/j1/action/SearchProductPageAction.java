package com.j1.action;

import com.j1.common.base.*;
import com.j1.pojo.vo.WebProductVo;
import com.j1.service.SearchProductService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by wangchuanfu on 20/7/17.
 */
@RestController
public class SearchProductPageAction extends WebSearchBaseAction {


    @Resource
    SearchProductService searchProductService;

    //搜索查询入口
    @RequestMapping(value = "/{keyword}/{attrs}/{requestInfo}/{suiji}")
    public void searchGoodsListIndex(HttpServletRequest request,
                                     HttpServletResponse response, @PathVariable String attrs,
                                     @PathVariable String requestInfo, @PathVariable String suiji,
                                     @PathVariable String keyword) {
        searchGoodsListIndexSeo(request, response, attrs, requestInfo,
                keyword);
    }

    @ResponseBody
    @RequestMapping(value = "/{keyword}/{attrs}/{requestInfo}")
    public void searchGoodsListIndexSeo(HttpServletRequest request,
                                        HttpServletResponse response, @PathVariable String attrs,

                                        @PathVariable String requestInfo, @PathVariable String keyword) {


        long start = System.currentTimeMillis();

        // 关键字
        String decodeKeyword = null;
        String luceneKeyword = null;

        // 会员Id
        String memberId = request.getParameter("memberId");
        // 分页信息
        Page<WebProductVo> pagination = null;
        Integer pageSize = 0;// 每页显示条数
        Integer pageNumInt = 0;
        String proCatalogId = null;// 分类id
        String orderBy = null;// 排序
        String pageNum = null;// 页码
        String searchType = null;// 0无结果，1整词查询，2切词and查询，3切词or查询
        String ids = "";
        // 分类统计
        List<Map<String, Object>> cataLogList = null;
        // 属性筛选
        List<Map<String, Object>> attrCondList = null;
        // (已选)属性筛选
        List<Map<String, Object>> attrParamList = null;
        // 热搜商品
        List<WebProductVo> hotList = null;
        // 热搜词
        List<String> hotWordList = null;
        // 相关推荐词
        List<Map<String, Object>> suggestWordList = new ArrayList<Map<String, Object>>();

        try {
            decodeKeyword = URLDecoder.decode(keyword, "utf-8");// 解码

            decodeKeyword = decodeKeyword.toLowerCase();
            // 去首位空格后，lucene转义后的内容
            luceneKeyword = decodeKeyword.trim();

            while (keyword.contains("%%")) {
                keyword = keyword.replace("%%", "%");
            }
        } catch (Exception e) {
            keyword = "%E6%AC%A7%E5%A7%86%E9%BE%99%E8%A1%80%E5%8E%8B%E8%AE%A1";// 给个默认值
            decodeKeyword = "欧姆龙血压计";
            luceneKeyword = "欧姆龙血压计";

        }

        String[] keywordSeacher = null;
        //如有空格，拆词
        if (luceneKeyword.indexOf(" ") > -1) {
            keywordSeacher = luceneKeyword.split(" ");
        } else {
            keywordSeacher = new String[]{luceneKeyword};
        }
        List<Map<String, Object>> keywordList = null;
        // 如果是拼音
        String pinyinKeyword = this.isPinyin(luceneKeyword, suggestWordList);


        // -----------------查找是否有相似品类---------------
        // 暂不查找

        // ---------------参数分解-----------------
        String[] infoArray = requestInfo.split("-");
        pageSize = Integer.valueOf(infoArray[0]);
        proCatalogId = infoArray[1];
        // 如果是默认值1，就显示28条
        pageSize = pageSize == 1 ? 28 : pageSize;

        orderBy = infoArray[2];
        pageNum = infoArray[3];

        //给默认值
        searchType = "and";
        // ------------------3、查询商品---------------------------------
        // 设置分页参数
        // ------------------页码转换--------------------
        pageNumInt = this.parsePageNum(pageNum);

        // ------------------确定查找类型--------------------
        //由于以前的查看以前的逻辑发现keywordSeacher是不会存在多个的，而且推荐词改版之后需要对这个数组重新赋值
        keywordSeacher = new String[]{luceneKeyword};


        // -------------------------- 4.1、查找分页数据--------------------
        pagination = this.searchPage(orderBy, pageNumInt, pageSize, keywordSeacher, proCatalogId, attrs, searchType, false);


    }

    @RequestMapping(value = "/searchgoods/{keyword}/{pageNo}/{pageSize}")
    @ResponseBody
    public String searchGoodsList(HttpServletRequest request,
                                     HttpServletResponse response,@PathVariable String keyword, @PathVariable Integer pageNo,
                                     @PathVariable Integer pageSize ) {

        return searchProductService.querySearchGoods(keyword, pageNo, pageSize);


    }
    //分页查询
    private Page<WebProductVo> searchPage(String orderBy, Integer pageNumInt, Integer pageSize, String[] luceneKeyword, String proCatalogId, String attrs,
                                          String searchType, boolean discount) {
        Page<WebProductVo> pagination = null;
        //排序
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, luceneKeyword[0]);
        Sort.Order order99 = new Sort.Order(Sort.Direction.DESC, "_score");
        Sort sort = null;
        // ------------------排序--------------------
        sort = new Sort("98", orderBy);
        sort.add(order99);
        PageRequest pageRequest = new PageRequest(pageNumInt, pageSize, sort);
        //搜索页面查询
        searchProductService.querySearch(null, null, null);

        return null;
    }

    private SheepPage initQueryPage(String originalKeyword,
                                    String previousKeyword, String catalogId, String attrs,
                                    String sort, String pageSize, String pageNumber, String searchType) throws Exception {
        SheepPage pageable = new SheepPage(originalKeyword, previousKeyword,
                catalogId, attrs, sort, pageSize, pageNumber, searchType);
        return pageable;


    }

    /**
     * 检查是否为拼音，转为匹配的中文
     *
     * @param luceneKeyword
     */
    protected String isPinyin(String luceneKeyword, List<Map<String, Object>> suggestWordList) {
        String tLuceneKeyword = luceneKeyword;
        try {
            if (luceneKeyword.matches("^[a-zA-Z]*")) {
                if (suggestWordList != null && suggestWordList.size() > 0) {
                    Object tObj = suggestWordList.get(0).get("word");
                    if (tObj != null && !"".equals(tObj.toString().trim()))
                        tLuceneKeyword = tObj.toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tLuceneKeyword;
    }

}
