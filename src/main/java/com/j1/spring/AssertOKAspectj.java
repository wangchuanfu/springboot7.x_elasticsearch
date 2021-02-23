package com.j1.spring;
//import com.sun.istack.internal.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.TypeVariable;

@Component
@Aspect
@Slf4j
public class AssertOKAspectj {

    @Pointcut("@annotation(com.j1.spring.AssertOK)")  //表示所有带有AssertOK的注解
    public void point(){

    }

//    @Pointcut("execution(* com.mb..*.*(..))")  //表示拦截所有com.mb包及子包下的所有的方法
//    public void point(){
//
//    }

    @Around(value = "point()")
    public Object assertAround(ProceedingJoinPoint pjp){

        //判断注解标识如果不为false则，进行登录
        Class<?> aClass = pjp.getTarget().getClass(); //先获取被织入增强处理的目标对象，再获取目标类的clazz
        String methodName = pjp.getSignature().getName(); //先获取目标方法的签名，再获取目标方法的名
        log.info("methodName: "+methodName);  // 输出目标方法名
        Class[] parameterTypes = ((MethodSignature) pjp.getSignature()).getParameterTypes(); //获取目标方法参数类型
        Object[] args = pjp.getArgs();  //获取目标方法的入参
        for (int i = 0; i < args.length; i++) {
            log.info("argsName: "+args[i]); //输出目标方法的参数
        }
        try {
            Method method = aClass.getMethod(methodName, parameterTypes);  //获取目标方法
            AssertOK annotation = method.getAnnotation(AssertOK.class);  //获取方法上的注解
            annotation.isLogin();  //获取注解函数值
            long starttime = System.currentTimeMillis();
            Object proceed = pjp.proceed();  //执行目标方法
            long exctime = System.currentTimeMillis() - starttime;
            log.info("执行时间："+exctime + "毫秒");
            log.info("proceed: "+proceed);  //打印目标方法的return结果
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return "aop的返回值";
    }
}
