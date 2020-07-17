package com.j1.common.utils;

import org.elasticsearch.common.text.Text;

/**
 * 
 * @包名 com.huayuan.search.utils
 * @类名 HighlightUtils
 * @描述 把es高亮返回的Text数组转换成字符串
 * 
 * @author 槐实(karlspace7@gmail.com)
 * @version 上午9:22:10, 2014年3月4日
 */
public class HighlightUtils
{

	public static String textToString(Text[] a)
	{
		if (a == null)
			return "null";
		int iMax = a.length - 1;
		if (iMax == -1)
			return "";

		StringBuilder b = new StringBuilder();
		for (int i = 0;; i++)
		{
			b.append(String.valueOf(a[i]));
			if (i == iMax)
				return b.toString();
		}
	}
}
