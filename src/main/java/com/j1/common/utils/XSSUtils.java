package com.j1.common.utils;

/**
 * 防XSS攻击脚本
 * 
 * @author Administrator
 * 
 */
public class XSSUtils
{

	public static String protectAgainstXSS(String html)
	{
		html = html.replace("<", "〈");
		html = html.replace(">", "〉");
		html = html.replace("(", "（");
		html = html.replace(")", "）");
		html = html.replace("\"", "");
		return html;
	}

}
