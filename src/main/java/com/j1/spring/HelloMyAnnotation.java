package com.j1.spring;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
/**
 * 自定义注解
 */
public @interface HelloMyAnnotation {
}
