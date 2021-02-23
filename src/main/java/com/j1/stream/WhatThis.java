package com.j1.stream;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WhatThis {
    public void whatThis() {
      //  在lambda中，this不是指向lambda表达式产生的那个SAM对象，而是声明它的外部对象。
        List<String> proStrs = Arrays.asList(new String[]{"Ni","Hao","Lambda"});
        List<String> execStrs =  proStrs.stream().map(
                str->{
                    System.out.println(this.getClass().getName());
                    return str.toLowerCase();
                }
        ).collect(Collectors.toList());
        execStrs.forEach(System.out::println);
    }
        @Test
    public void test1(){
            WhatThis wt = new WhatThis();
            wt.whatThis();

        }
    }
