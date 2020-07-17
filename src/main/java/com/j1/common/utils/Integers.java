package com.j1.common.utils;

/**
 * Created by wangchuanfu on 20/7/16.
 */
public class Integers {

    /**
     * 字符解析成数字
     *
     * @param digit
     * @return
     */
    public static Integer parseInteger(String digit)
    {
        return parseInteger(digit, 0);
    }

    /**
     * 字符解析成数字
     *
     * @param digit
     * @param defaultDigit
     * @return
     */
    public static Integer parseInteger(String digit, Integer defaultDigit)
    {
        try
        {
            return Integer.parseInt(digit);
        }
        catch (Exception e)
        {
            return defaultDigit;
        }
    }
}
