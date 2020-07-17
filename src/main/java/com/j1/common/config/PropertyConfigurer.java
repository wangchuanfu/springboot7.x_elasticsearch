package com.j1.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by wangchuanfu on 20/7/16.
 */
public class PropertyConfigurer  extends PropertyPlaceholderConfigurer {


    private static Logger logger = LoggerFactory.getLogger(PropertyConfigurer.class);

    private static Map<String, Object> ctxPropertiesMap;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory,
                                     Properties props)throws BeansException {
        if(logger.isDebugEnabled())
            try
            {
                this.loadProperties(new Properties());
            }
            catch (IOException e)
            {
                logger.error(e.getMessage(), e);
            }

        super.processProperties(beanFactory, props);
        //load properties to ctxPropertiesMap
        ctxPropertiesMap = new HashMap<String, Object>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            ctxPropertiesMap.put(keyStr, value);
            if(logger.isDebugEnabled())
                logger.debug("key[" + keyStr + "]:value[" + value + "]");
        }
    }

    //static method for accessing context properties
    public static Object getContextProperty(String name) {
        try {
            return ctxPropertiesMap.get(name);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            return null;
        }
    }

}
