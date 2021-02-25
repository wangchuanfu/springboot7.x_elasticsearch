package com.j1.service;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by wangchuanfu on 20/7/31.
 */
public abstract class AbstractEsService<T> {

    /**
     *  在项目启动的时候此抽象类中init方法会先执行,
     *
     *  1,因为springBoot 在启动的时候,基于service注解spring会先实例化searchGoodsServiceImpl,
     *  2,searchGoodsServiceImpl继承了父类AbstractEsService,所以先加载父类AbstractEsService,
     *  3,在加载父类AbstractEsService的时候,先执行静态代码块,在去执行此类的空参构造方法.
     *  4,接着执行空参构造方法中的init()方法,通过反射机制获取泛型T 的实例对象.
     */

    /**
     * 泛型T的类型
     */
    protected Class<T> clazz;
    /**
     * 泛型T中的属性
     */
    protected Field[] fields;
    /**
     * 索引名
     */
    protected String indexName;
    /**
     * 索引类型
     */
    protected String[] indexTypes;

    /**
     * 初始化时，从超类中找到泛型对象，从泛型对象的EsIndex注解中获得索引名、索引类型
     */
    public AbstractEsService() {
        //init();
    }

    private void init() {
        try {
            // 获得超类，e.g.：Service2 extends
            // Service1<Clazz>，Service2中执行，获得Service1的Type
            Class<?> tclazz = getClass();
            // $$EnhancerByCGLIB 代理对象无泛型，不执行取泛型操作
            if (tclazz.getName().indexOf("$$EnhancerByCGLIB") == -1) {
                Type type = getClass().getGenericSuperclass();

                // 从此类型实际类型参数的 Type对象的数组中获取泛型对象的实例
                Type trueType = ((ParameterizedType) type).getActualTypeArguments()[0];
                // 对象转型
                this.clazz = (Class<T>) trueType;

                // 获得T里面的所有字段
                fields = clazz.getDeclaredFields();
                Field.setAccessible(fields, true);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
