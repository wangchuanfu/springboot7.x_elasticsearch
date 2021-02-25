package com.j1.xiaoxiang.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式 工具类
 */
public class RegUtil {

    /**
     * 匹配非法字符
     */
    private static Pattern illegalCharPattern = Pattern.compile("((\\pP|\\pS){1,})||(\\d){5,}||[\\ud83c\\udc00-\\ud83c\\udfff]|[\\ud83d\\udc00-\\ud83d\\udfff]|[\\u2600-\\u27ff]");

    /**
     * 判断是否全为非法字符
     *
     * @return 是否全部为非法字符
     */
    public static boolean isAllIllegalChar(String str) {
        Matcher matcher = illegalCharPattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 判断包含非法字符
     *
     * @return 是否包含非法字符
     */
    public static boolean haveIllegalChar(String str) {
        Matcher matcher = illegalCharPattern.matcher(str);
        return matcher.find();
    }

    /**
     * 匹配汉字
     */
    private static Pattern containChinesePattern = Pattern.compile("[\u4e00-\u9fa5]");

    /**
     * 是否包含汉字
     *
     * @param str 待匹配的字符串
     * @return 是否包含汉字
     */
    public static boolean isContainChinese(String str) {
        Matcher m = containChinesePattern.matcher(str);
        return m.find();
    }


    /**
     * 匹配汉字、数字、空白
     */
    private static Pattern allChinesePattern = Pattern.compile("^[[\u4e00-\u9fa5]0-9\\s]+$");

    public static boolean isAllChinese(String str) {
        Matcher m = allChinesePattern.matcher(str);
        return m.matches();
    }

    /**
     * 匹配英语大小写字母、数字、空白
     */
    private static Pattern allEnglishPattern = Pattern.compile("^[a-zA-z0-9\\s]+$");

    public static boolean isAllEnglish(String str) {
        Matcher m = allEnglishPattern.matcher(str);
        return m.matches();
    }


    /**
     * 替换字符串中特殊字符为空格
     *
     * @param s 输入的字符串
     * @return 替换之后的字符串
     */
    public static String replaceChar(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // These characters are part of the query syntax and must be escaped
            if (c == '\\' || c == '+' || c == '!' || c == '(' || c == ')' || c == ':' || c == '.'
                    || c == '^' || c == '[' || c == ']' || c == '\"' || c == '{' || c == '}' || c == '~'
                    || c == '*' || c == '?' || c == '|' || c == '&' || c == '/' || c == '（' || c == '）') {
                sb.append(" ");
            } else {
                sb.append(c);
            }
        }
        return sb.toString().trim();
    }

    /**
     * 是否含有英文
     *
     * @return boolean
     */
    public static boolean isHavingChar(String str) {
        //【含有英文】true
        String regex1 = ".*[a-zA-z].*";
        return str.matches(regex1);
    }

    /**
     * 判断字符串bai是否du全为英文字母zhi，是则返回 true
     *
     * @return boolean
     */
    public static boolean isNoShuZi(String str) {
        boolean isWord = str.matches("[a-zA-Z]+");
        return isWord;
    }

    public static String realSkWord(String str) {
        return str.trim().toLowerCase().replaceAll("\\s+", " ");
    }

    // 判断一个字符串是否都为数字
    public static boolean isDigit(String strNum) {
        return strNum.matches("[0-9]{1,}");
    }

    //截取数字
    public static String getNumbers(String content) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    // 截取非数字
    public static String splitNotNumber(String content) {
        Pattern pattern = Pattern.compile("\\D+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    // 判断一个字符串是否含有数字
    public static boolean HasDigit(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }

    public static String getRealSkDaiKongGe(String sk) {
        StringBuilder newStr = new StringBuilder();
        for (int i = 0; i < sk.length(); i++) {
            newStr.append(sk.charAt(i));
            int j = i + 1;
            if (j < sk.length() && !Character.isDigit(sk.charAt(i)) && Character.isDigit(sk.charAt(j)) == true) {
                newStr.append(" ");
            }
            if (j < sk.length() && Character.isDigit(sk.charAt(i)) == true && Character.isDigit(sk.charAt(j)) == false) {
                newStr.append(" ");
            }
        }
        return RegUtil.realSkWord(newStr.toString());
    }

    public static String getRealSkNoShuZi(String sk) {
        String realSkDaiKongGe = getRealSkDaiKongGe(sk);
        StringBuilder newStr = new StringBuilder();
        for (int i = 0; i < realSkDaiKongGe.length(); i++) {
            if (Character.isDigit(realSkDaiKongGe.charAt(i)) == false) {
                newStr.append(realSkDaiKongGe.charAt(i));
            }
        }
        return RegUtil.realSkWord(newStr.toString());
    }
}
