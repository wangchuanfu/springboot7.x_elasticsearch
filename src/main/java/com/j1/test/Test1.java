package com.j1.test;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by wangchuanfu on 20/9/14.
 */
public class Test1 {

    public static void main(String[] args) {

        Map<String, String> collect = Stream.of("a", "b", "c", "a").collect(Collectors.toMap(x -> x, x -> x + x, (oldVal, newVal) -> newVal));
        collect.forEach((k, v) -> System.out.println(k + ":" + v));
    }
}
