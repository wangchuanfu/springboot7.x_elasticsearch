package com.j1.spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 配置切面
 */
@Aspect
@Component
public class HelloAspectTest {
    //@Pointcut("execution(public * com.example.demo.controller.HelloController.add*(..))")
    //使用自定义注解,同时也可以将@Pointcut改为(public * com.j1.spring.*.*(..)) && @annotation(com.j1.spring.HelloMyAnnotation)
    @Pointcut("execution(public * com.j1.spring.HelloController.add*(..)) && @annotation(com.j1.spring.HelloMyAnnotation)" )

    public void addAdvice(){}
    @Around("addAdvice()")
    public Object Interceptor(ProceedingJoinPoint pjp){
        Object result = null;
        Object[] args = pjp.getArgs();
        if(args != null && args.length >0) {
            String deviceId = (String) args[0];
            if(!"03".equals(deviceId)) {
                return "no anthorization";
            }
        }
        try {
            result =pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }
}
