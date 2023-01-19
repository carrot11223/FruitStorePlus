package com.carrot.utils;

/**
 * 封装一个工具类，用来判断字符串是否为空
 */
public class StringUtils {
    public static boolean isEmpty(String s){
        return s==null || "".equals(s);
    }
    public static boolean isNotEmpty(String s){
        return !isEmpty(s);
    }
}
