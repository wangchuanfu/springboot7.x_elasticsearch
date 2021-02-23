package com.j1.spring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HelloController {
    //这是加上自定义注解
    @HelloMyAnnotation
    @RequestMapping("/add1")
    public String addData1(String deviceId) {
        return "success";
    }

    @RequestMapping("/add2")
    public String addData2(String deviceId) {
        return "success";
    }
    @RequestMapping("/add3")
    public String addData3(String deviceId) {
        return "success";
    }
}
