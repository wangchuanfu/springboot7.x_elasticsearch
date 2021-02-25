package com.j1.spring;

import org.springframework.stereotype.Component;

@Component

public class QueryAccount {
    @AssertOK
    public String queryAccount() {

        System.out.println("账户查询成功....");
        return "success";
    }
}
