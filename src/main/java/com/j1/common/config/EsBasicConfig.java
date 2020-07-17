package com.j1.common.config;

import com.j1.common.utils.BooleanUtils;
import com.j1.common.utils.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static org.elasticsearch.common.util.ArrayUtils.concat;

/**
 * Created by wangchuanfu on 20/7/16.
 */
public abstract class EsBasicConfig {

    protected static Logger CONFIG_LOG = LoggerFactory.getLogger(EsBasicConfig.class);

    /**
     * ES配置Properties
     */
//	private final static Properties PROP = new Properties();

    /**
     * 同步锁
     */
    private final static ReadWriteLock LOCK = new ReentrantReadWriteLock();

    protected static String LOCAL_PATH = null;

//	static
//	{
//		try
//		{
//			// 判断操作系统，对不同系统获取property配置使用不同路径
//			LOCAL_PATH = EsBasicConfig.class.getClassLoader()
//					.getResource("esclient-conf.properties").getPath();
//			PROP.load(new FileInputStream(LOCAL_PATH));
//		}
//		catch (IOException e)
//		{
//			CONFIG_LOG.error(ExceptionUtils.configErrorFormat1(EsBasicConfig.class), e);
//		}
//	}

    public static String getStrProp(String key)
    {
        String tmp = null;
        LOCK.readLock().lock();
        try
        {
//			tmp = PROP.getProperty(key);
            Object tmpObj = PropertyConfigurer.getContextProperty(key);
            if(tmpObj != null)
                tmp = tmpObj.toString();
            else
                tmp = "";
        }
        catch (Exception e)
        {
            CONFIG_LOG.error(ExceptionUtils.configErrorFormat2(key), e);
        }
        finally
        {
            LOCK.readLock().unlock();
        }
        return tmp;
    }

    public static Float getFloatProp(String key)
    {
        Float tmp = null;
        LOCK.readLock().lock();
        try
        {
            tmp = Float.parseFloat(getStrProp(key));
        }
        catch (Exception e)
        {
            CONFIG_LOG.error(ExceptionUtils.configErrorFormat2(key), e);
        }
        finally
        {
            LOCK.readLock().unlock();
        }
        return tmp;
    }

    public static Integer getIntProp(String key)
    {
        Integer tmp = null;
        LOCK.readLock().lock();
        try
        {
            tmp = Integer.parseInt(getStrProp(key));
        }
        catch (Exception e)
        {
            CONFIG_LOG.error(ExceptionUtils.configErrorFormat2(key), e);
        }
        finally
        {
            LOCK.readLock().unlock();
        }
        return tmp;
    }

    public static Boolean getBoolProp(String key, Boolean defaultValue)
    {
        Boolean tmp = null;
        LOCK.readLock().lock();
        try
        {
            tmp = BooleanUtils.parseBoolean(getStrProp(key), defaultValue);
        }
        catch (Exception e)
        {
            CONFIG_LOG.error(ExceptionUtils.configErrorFormat2(key), e);
        }
        finally
        {
            LOCK.readLock().unlock();
        }
        return tmp;
    }

//	public static boolean syncSetProp(String key, String value)
//	{
//		LOCK.writeLock().lock();
//		try
//		{
//			PROP.setProperty(key, value);
//		}
//		catch (Exception e)
//		{
//			CONFIG_LOG.error(ExceptionUtils.configErrorFormat2(key), e);
//		}
//		finally
//		{
//			LOCK.writeLock().unlock();
//		}
//		return true;
//	}

    public static String[] getSplitStr(String key, String splitSign)
    {
        String[] tmp = null;
        LOCK.readLock().lock();
        try
        {
            String esHosts = getStrProp(key);
            if (esHosts != null && !"".equals(esHosts.trim()))
                tmp = esHosts.split(splitSign);
        }
        catch (Exception e)
        {
            CONFIG_LOG.error(ExceptionUtils.configErrorFormat2(key), e);
        }
        finally
        {
            LOCK.readLock().unlock();
        }
        return tmp;
    }

    /**
     * 获取固定格式的prop转换成Map后返回
     * @param key
     * @param splitSign
     * @return
     */
    public static Map<String, String[]> getStrMap(String key, String splitSign)
    {
        Map<String, String[]> tmp = new HashMap<String, String[]>();
        LOCK.readLock().lock();
        try
        {
            String esParams = EsBasicConfig.getStrProp(key);
            if (esParams != null && !"".equals(esParams.trim()))
            {
                String[] tmps = esParams.split(splitSign);
                for (String ttmp : tmps)
                {
                    String[] ttmps = ttmp.split(":");
                    if (ttmps != null && ttmps.length != 0)
                    {
                        String[] source = null;
                        if (tmp.containsKey(ttmps[0]))
                            source = tmp.get(ttmps[0]);
                        String[] destintation = concat(source,
                                new String[] { ttmps[1] });
                        tmp.put(ttmps[0], destintation);
                    }
                }
            }
        }
        catch (Exception e)
        {
            CONFIG_LOG.error(ExceptionUtils.configErrorFormat2(key), e);
        }
        finally
        {
            LOCK.readLock().unlock();
        }
        return tmp;
    }

    /**
     * 为对象赋值，如果新值为空，就取对象原本的值
     * @param obj
     * @param newValue
     * @return
     */
    public static <T>T setObjectNotNull(T obj, T newValue)
    {
        if(newValue != null)
            obj = newValue;
        return obj;
    }
}
