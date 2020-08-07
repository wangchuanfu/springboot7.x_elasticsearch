package com.j1.action;

import com.j1.result.ArrayUtil;
import com.j1.result.ResultMsg;
import com.j1.result.ServiceMessage;
import com.j1.result.SoaApiBaseAction;
import com.j1.service.InitIndexService;
import com.j1.service.SuggestionSearchService;
import com.j1.type.MsgStatus;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by wangchuanfu on 20/7/10.
 */

@RestController
@Slf4j
public class SearchGoodsAction extends SoaApiBaseAction {

    //搜索提示
    @Autowired
    SuggestionSearchService suggestionSearchService;
    @Resource
    InitIndexService initIndexService;

    //根据关键字搜索
    @RequestMapping("/search/{keyword}/{pageNo}/{pageSize}")
    public List searchPage(@PathVariable("keyword") String keyword,
                           @PathVariable("pageNo") Integer pageNo,
                           @PathVariable("pageSize") Integer pageSize) throws Exception {
        if (keyword == null) {
            keyword = "java";//设置默认关键字
        }
        List<Map<String, Object>> list = initIndexService.search(keyword, pageNo, pageSize);

        return list;

    }

    //搜索提示
    @RequestMapping("/search/suggest")
    public List<String>  searchSuggest(HttpServletRequest request,
                                      HttpServletResponse response, @RequestParam(required = false) String keyword) {
        try {
            JSONObject resultObject = new JSONObject();

            if (keyword == null || "".equals(keyword.trim())) {
                return null;
            }
            keyword = keyword.trim();
            ServiceMessage<List<String>> listSuggest = suggestionSearchService.querySuggest(keyword);

            if (listSuggest.getStatus() == MsgStatus.NORMAL) {
                resultObject.put("listSuggest", listSuggest);
                this._result.setObjData(resultObject);
            }
            _result.put(
                    "listSuggestTest",
                    ArrayUtil.resultIsEmp(listSuggest) ? new JSONArray() : JSONArray
                            .fromObject(listSuggest));
            this._result.setObjData(resultObject);

            //this.setResultInfo(MsgStatus.NORMAL.getCode(), ResultMsg.Common.OK).write(request,response);

            return listSuggest.getResult();
        } catch (Exception e) {
            log.error("suggestionSearchService.querySuggest:" + e);

        }
        return null;
    }
}
