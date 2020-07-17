package com.j1.action;

import com.j1.pojo.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangchuanfu on 20/7/16.
 */
@RestController
@Slf4j
public class LogbackController {
    @RequestMapping("/testlogback")
    public String testEsItem(@RequestParam(value = "userName") String userName) {
       log.info("==========test logback============");
        return userName;
    }
}
