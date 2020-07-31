package com.j1.init;

import com.j1.pojo.WebProductVo;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by wangchuanfu on 20/7/30.
 */
@Order // @Order注解可以改变执行顺序，越小越先执行
@Component
public class MyApplicationRunner<T> implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        /**
         * 项目初始化的时候执行
         */
        System.out.println("MyApplicationRunner==========hehe======" + applicationArguments);
    }
}
