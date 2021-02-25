package com.j1.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created by wangchuanfu on 20/7/17.
 */
@Component
public class HashMapToBeanUtils extends HashMap<String, Object> {

    public Object toBean(Class<?> clazz) {
        try {
            Object obj = clazz.newInstance();
            BeanUtils.populate(obj, this);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
