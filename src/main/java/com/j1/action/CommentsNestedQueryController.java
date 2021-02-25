package com.j1.action;

import com.j1.service.CommentsNestedQueryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CommentsNestedQueryController {
    @Resource
    CommentsNestedQueryService commentsNestedQueryService;

    @RequestMapping(value = "/getCommments")
    @ResponseBody
    public String commentsNestedQuery() {
        commentsNestedQueryService.getCommments();
        return "ok";
    }
}
