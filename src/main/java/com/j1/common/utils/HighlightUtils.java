package com.j1.common.utils;

import org.elasticsearch.common.text.Text;


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
