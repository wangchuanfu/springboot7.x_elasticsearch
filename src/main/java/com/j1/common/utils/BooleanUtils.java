package com.j1.common.utils;

/**
 * Created by wangchuanfu on 20/7/16.
 */
public class BooleanUtils {

    public static boolean parseBoolean(String value, boolean defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        return !(value.equals("false") || value.equals("0")
                || value.equals("off") || value.equals("no"));
    }

    public static Boolean parseBoolean(String value, Boolean defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        return !(value.equals("false") || value.equals("0")
                || value.equals("off") || value.equals("no"));
    }

}
