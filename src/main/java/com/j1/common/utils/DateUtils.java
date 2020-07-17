package com.j1.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * 
 * @包名 com.huayuan.search.utils
 * @类名 DateUtils
 * @描述 日期相关工具
 * 
 * @author 槐实(karlspace7@gmail.com)
 * @version 上午9:52:59, 2014年3月19日
 */
public class DateUtils {
	public enum Formats {

		ES("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"), NORMAL("yyyy-MM-dd HH:mm:ss"), YEAR_MOUTH_DAY("yyyyMMdd");
		private final String STYLE;

		private Formats(String style) {
			this.STYLE = style;
		}
	}

	public static String formatDate(Object obj) {
		return formatDate(obj, Formats.NORMAL);
	}

	public static String formatDate(Date date, Formats style) {
		return formatDate(date, style);
	}

	public static String formatDate(Object obj, Formats style) {
		String dateStr = "";
		if (obj instanceof Date) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(style.STYLE);
				dateStr = sdf.format((Date) obj);
			} catch (Exception e) {
				dateStr = "";
				e.printStackTrace();
			}
		}
		return dateStr;
	}

	public static String nowTime() {
		return nowTime(Formats.ES);
	}

	public static String nowTime(Formats style) {
		Date date = new Date();
		String dateStr = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(style.STYLE);
			dateStr = sdf.format(date);
		} catch (Exception e) {
			dateStr = "";
			e.printStackTrace();
		}
		return dateStr;
	}

	public static String nowTimeNormal() {
		Date date = new Date();
		String dateStr = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(Formats.NORMAL.STYLE);
			dateStr = sdf.format(date);
		} catch (Exception e) {
			dateStr = "";
			e.printStackTrace();
		}
		return dateStr;
	}

	/**
	 * 日期验证
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isDate(Object object) {
		return object instanceof Date || Pattern.matches("[1-2][0-9][0-9][0-9]-[0-9][0-9].*", object.toString());
	}

	/***
	 * String型日期转为long型
	 * 
	 * @param date
	 *            String型日期
	 * @return long 日期
	 * @throws ParseException
	 */
	public static long dateAllToLong(String source, String format) {
		try {
			return getDateParser(format).parse(source).getTime();
		} catch (ParseException e) {
			try {
				return getDateParser(format).parse(source).getTime();
			} catch (ParseException e1) {
				return -1;
			}
		}

	}

	public static SimpleDateFormat getDateParser(String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat;
	}

}
