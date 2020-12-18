package com.j1.test;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Test2 {
    public static void main(String[] args) {
        String s = numberToChinese("95折");
        System.out.println("s: "+s);

    }

    /**
     *
     * @param str
     */

    public static void mathToChinese(String str) {
        String staffcode = StringUtils.substringBefore(str, str.replaceAll("^[a-zA-Z]*\\d+", ""));
        String[] s2 = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        StringBuffer sb=new StringBuffer();
        for (char c : staffcode.toCharArray()) {
            sb.append(s2[Integer.parseInt(String.valueOf(c))]);
        }
        System.out.println("s: "+sb);

    }

    public static String numberToChinese(String numberStr)
    {
        String numStr = "0123456789";
        String chineseStr = "零一二三四五六七八九";
        char[] c = numberStr.toCharArray();
        for (int i = 0; i < c.length; i++)
        {
            int index = numStr.indexOf(c[i]);
            if (index != -1)
                c[i] = chineseStr.toCharArray()[index];
        }
        return new String(c);
    }
}
