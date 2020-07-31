package com.j1.action;

import com.j1.service.SearchProductService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangchuanfu on 20/7/31.
 */
@RestController
public class SearchProductAction {
    @Resource
    SearchProductService searchProductService;
    @RequestMapping(value = "/searchproduct/{keyword}/{pageNo}/{pageSize}")
    @ResponseBody
    public String searchGoodsList(HttpServletRequest request,
                                  HttpServletResponse response, @PathVariable String keyword, @PathVariable Integer pageNo,
                                  @PathVariable Integer pageSize ) {

        return searchProductService.querySearchGoods(keyword, pageNo, pageSize);

    }
}
