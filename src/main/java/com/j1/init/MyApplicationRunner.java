package com.j1.init;

/**
 * Created by wangchuanfu on 20/7/30.

@Order // @Order注解可以改变执行顺序，越小越先执行
@Component
public class MyApplicationRunner<T> implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

        System.out.println("MyApplicationRunner==========hehe======" + applicationArguments);
    }
}
 */