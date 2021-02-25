package com.j1.action;

import com.j1.pojo.Member;
import com.j1.pojo.News;
import com.j1.service.NewsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by wangchuanfu on 20/7/15.
 */
//mybatis 测试
@RestController
public class NewsController {

    @Resource
    NewsService newsService;

    //查询这是mysql
    @RequestMapping(value = {"/getNewsInfo/{id}"})
    @ResponseBody
    public News selectNewsInfo(@PathVariable("id") Long id) {
        return newsService.selectNewsInfo(id);
    }

    //查询 这是oracle  springboot 可以同时配置多数据源,待写
    @RequestMapping(value = {"/getMemberById/{id}"})
    @ResponseBody
    public Member getMemberById(@PathVariable("id") Long id) {
        return newsService.getMemberById(id);
    }

}
