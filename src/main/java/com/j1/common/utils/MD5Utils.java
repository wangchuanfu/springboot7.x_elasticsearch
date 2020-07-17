package com.j1.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @包名 com.huayuan.search.utils
 * @类名 MD5Utils
 * @描述 MD5加密类
 * 
 * @author 槐实(karlspace7@gmail.com)
 * @version 上午12:29:28, 2014年4月2日
 */
public class MD5Utils
{
	/**
	 * MD5加密类
	 * 
	 * @param str
	 *            要加密的字符串
	 * @return 加密后的字符串
	 */
	public static String code(String str)
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte[] byteDigest = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < byteDigest.length; offset++)
			{
				i = byteDigest[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			// 32位加密
			return buf.toString();
			// 16位的加密
			// return buf.toString().substring(8, 24);
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
			return null;
		}

	}
	
	public final static String MD5(String s)
	{
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try
		{
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++)
			{
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public final static String createKey()
	{
		String nowTime = DateUtils.nowTime(DateUtils.Formats.YEAR_MOUTH_DAY);
		return MD5(nowTime+"huayuan");
	}
	
	public final static boolean isSame(String md5)
	{
		if(md5 != null && md5.equals(createKey()))
			return true;
		return false;
	}
	
	public static void main(String[] args)
	{
		System.out.println(createKey());
	}
}
