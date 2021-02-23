//package com.j1.spring;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceUnit;
//
//@Component
//@Aspect
//public class ControllerAspect {
//    /**
//     * 参数说明:
//     * 需要说明的是，在以下例子中，我们即可以只用@Around注解，并设置条件，见方法run1()；
//     * 也可以用@Pointcut和@Around联合注解，见方法pointCut2()和run2()，这2种用法是等价的。
//     * 如果我们还想利用其进行参数的修改，则调用时必须用joinPoint.proceed(Object[] args)方法，
//     * 将修改后的参数进行回传。如果用joinPoint.proceed()方法，则修改后的参数并不会真正被使用。
//     */
//
//    private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);
//
//    @PersistenceUnit
//    private EntityManager entityManager;
//
//    /**
//     * 调用controller包下的任意类的任意方法时均会调用此方法
//     */
//    @Around("execution(* com.j1.spring.*.*(..))")
//    public Object run1(ProceedingJoinPoint joinPoint) throws Throwable {
//        //获取方法参数值数组
//        Object[] args = joinPoint.getArgs();
//        //得到其方法签名
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        //获取方法参数类型数组
//        Class[] paramTypeArray = methodSignature.getParameterTypes();
//        if (EntityManager.class.isAssignableFrom(paramTypeArray[paramTypeArray.length - 1])) {
//            //如果方法的参数列表最后一个参数是entityManager类型，则给其赋值
//            args[args.length - 1] = entityManager;
//        }
//        logger.info("请求参数为{}",args);
//        //动态修改其参数
//        //注意，如果调用joinPoint.proceed()方法，则修改的参数值不会生效，必须调用joinPoint.proceed(Object[] args)
//        Object result = joinPoint.proceed(args);
//        logger.info("响应结果为{}",result);
//        //如果这里不返回result，则目标对象实际返回值会被置为null
//        return result;
//    }
//
//    @Pointcut("execution(* com.j1.spring.*.*(..))")
//    public void pointCut2() {}
//
//    @Around("pointCut2()")
//    public Object run2(ProceedingJoinPoint joinPoint) throws Throwable {
//        //获取方法参数值数组
//        Object[] args = joinPoint.getArgs();
//        //得到其方法签名
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        //获取方法参数类型数组
//        Class[] paramTypeArray = methodSignature.getParameterTypes();
//        if (EntityManager.class.isAssignableFrom(paramTypeArray[paramTypeArray.length - 1])) {
//            //如果方法的参数列表最后一个参数是entityManager类型，则给其赋值
//            args[args.length - 1] = entityManager;
//        }
//        logger.info("请求参数为{}",args);
//        //动态修改其参数
//        //注意，如果调用joinPoint.proceed()方法，则修改的参数值不会生效，必须调用joinPoint.proceed(Object[] args)
//        Object result = joinPoint.proceed(args);
//        logger.info("响应结果为{}",result);
//        //如果这里不返回result，则目标对象实际返回值会被置为null
//        return result;
//    }
//}
