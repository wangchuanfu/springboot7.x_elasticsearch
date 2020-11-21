package com.j1.stream;

/**
 * Created by wangchuanfu on 20/10/10.
 */
public class LambdaTest implements LambdaInterface {
    public static void main(String[] args) {

        //无参无返回
        NoReturnNoParam noReturnNoParam = () -> {
            System.out.println("NoReturnNoParam");
        };
        noReturnNoParam.method();
    }


}
