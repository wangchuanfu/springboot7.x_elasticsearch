package com.j1.common.utils;

import java.util.Arrays;

/**
 * 
 * @包名 com.huayuan.search.utils
 * @类名 ArrayUtils
 * @描述 数组相关工具
 * 
 * @author 槐实(karlspace7@gmail.com)
 * @version 上午9:52:31, 2014年3月19日
 */
public class ArrayUtils
{
	/**
	 * 简单的数组非空校验
	 * @param values
	 * @return
	 */
	public static boolean arrIsNotEmpty(Object[] values)
	{
		if (values == null)
		{
			return false;
		}
		else if (values.length == 0)
		{
			return false;
		}
		else if (values[0] == null)
		{
			return false;
		}
		else if (values[0].toString().trim().isEmpty())
		{
			return false;
		}
		return true;
	}
	
	/**
	 * 简单的数组非空校验
	 * @param values
	 * @return
	 */
	public static boolean arrIsEmpty(Object[] values)
	{
		return !arrIsNotEmpty(values);
	}
	
	/**
	 * 数组合并
	 * @param first
	 * @param second
	 * @return
	 * @throws Exception
	 */
	public static <T> T[] concat(T[] first, T[] second) throws Exception
	{
		if (first == null)
			return second;
		if (second == null)
			return first;
		T[] result = Arrays.copyOf(first, first.length + second.length);
		System.arraycopy(second, 0, result, first.length, second.length);
		return result;
	}
	
}
