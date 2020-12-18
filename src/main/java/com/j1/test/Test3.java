package com.j1.test;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Test3 {
    public static void main(String[] args) {


        StringBuffer characters = new StringBuffer();
        StringBuffer letter = new StringBuffer();
        StringBuffer number = new StringBuffer();

        String str = "中国abc123";
        char[] strArr = str.toCharArray();
        for (char string : strArr) {
            // 判断是否为字母
            if ((string + "").matches("[a-z]") || (string + "").matches("[A-Z]")) {
                letter.append(string + "  ");
            }
            // 判断是否为数字
            if ((string + "").matches("[0-9]")) {
                number.append(string + "  ");
            }
            // 判断是否为汉字
            if (isChineseChar(string + "")) {
                characters.append(string + "  ");
            }
        }
        System.out.println(characters.toString());
        System.out.println(letter.toString());
        System.out.println(number.toString());
    }

    /**
     * 判断是否为汉字
     *
     * @param str 字符串
     * @return
     */
    public static boolean isChineseChar(String str) {
        boolean temp = false;
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            temp = true;
        }
        return temp;
    }

}
