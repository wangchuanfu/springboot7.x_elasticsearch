package com.j1.common.utils;

/**
 * Created by wangchuanfu on 20/7/16.
 */
public class ExceptionUtils {
    /**
     * 配置文件读取错误时的信息格式
     *
     * @param clazz
     * @return
     */
    public static String configErrorFormat1(Class<?> clazz) {
        return "###" + clazz.getSimpleName() + "读取错误！###";
    }

    /**
     * Properties对象读取错误时的信息格式
     *
     * @param obj
     * @return
     */
    public static String configErrorFormat2(Object obj) {
        return "###配置文件读取错误！key=" + obj.toString() + "####";
    }

    /**
     * EsService初始化错误
     *
     * @param clazz
     * @return
     */
    public static String serviceInitErrorFormat1(Class<?> clazz) {
        return "###" + clazz.getSimpleName() + "初始化错误！###";
    }

    /**
     * EsService添加索引时错误
     *
     * @param clazz
     * @return
     */
    public static String serviceIndexErrorFormat1(Class<?> clazz) {
        return "###" + clazz.getSimpleName() + "添加索引时错误！###";
    }

    /**
     * EsService实体类转化为Map时
     *
     * @param clazz
     * @return
     */
    public static String serviceSetValueErrorFormat1(Class<?> clazz) {
        return "###" + clazz.getSimpleName() + "实体类转化为Map时！###";
    }
}
