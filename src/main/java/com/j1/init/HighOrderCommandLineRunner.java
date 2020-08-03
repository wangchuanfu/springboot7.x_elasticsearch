package com.j1.init;

import com.j1.common.utils.ExceptionUtils;
import com.j1.pojo.WebProductVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by wangchuanfu on 20/7/30.

@Slf4j
@Component
public class HighOrderCommandLineRunner<T> implements CommandLineRunner,Ordered {


    @Override
    public void run(String... strings) throws Exception {


        log.info("i am highOrderRunner");
        System.out.println("MyApplicationRunner==========haha======" );
         *
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



                // 获得es索引注解，得到indexName和indexType
                EsIndex esIndex = clazz.getAnnotation(EsIndex.class);
                if (esIndex == null) {
                    throw new NotEsIndexException();
                }
                // 获得T里面设置的索引名
                setIndexName(esIndex.indexName());
                // 获得T里面设置的索引类型
                setIndexTypes(esIndex.indexTypes());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getOrder() {

        return Integer.MIN_VALUE+1;
    }
}  */
