package com.j1.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by wangchuanfu on 20/7/16.
 */
public class WebUtils {

    public static String convertUnicode(String unicodeStr) throws UnsupportedEncodingException {
        if (StringUtils.isNotBlank(unicodeStr) && unicodeStr.startsWith("%")) {
            while (unicodeStr.contains("%25")) {
                unicodeStr = unicodeStr.replace("%25", "%");
            }

            if (unicodeStr.length() == 3) {
                unicodeStr = URLDecoder.decode(unicodeStr, "utf-8");
            } else {
                boolean flag = true;
                for (int i = -1; i < 10 && (i + 3) < unicodeStr.length(); i += 3) {
                    if (unicodeStr.indexOf("%", i) != (i + 1))
                        flag = false;
                }
                if (flag) {
                    unicodeStr = URLDecoder.decode(unicodeStr, "utf-8");
                }
            }
            return unicodeStr;
        } else {
            return unicodeStr;
        }
    }

    public static String convertGetParams(String isoStr) throws UnsupportedEncodingException {
        if (StringUtils.isNotBlank(isoStr)) {
            return new String(isoStr.getBytes("iso8859-1"), "UTF-8");
        } else {
            return isoStr;
        }
    }

    public static void main(String[] args) throws Exception {
        String aa = "%E6%AC%A7%E5%A7%86%E9%BE%99%E8%A1%80%E5%8E%8B%E8%AE%A1";// 给个默认值
        System.out.println(convertUnicode(aa));
    }
}
