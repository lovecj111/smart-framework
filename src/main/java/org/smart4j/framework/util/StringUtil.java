package org.smart4j.framework.util;

import org.apache.commons.lang3.StringUtils;

/**
 *  字符串工具类
 */
public final class StringUtil {

    /**
     *  判断字符串是否为空
     */
    public static boolean isEmpty(String str){
        if(str != null){
            str.trim();
        }
        return StringUtils.isEmpty(str);
    }


    /**
     *  判断字符串是否非空
     */
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

    /**
     * 拆分字符串为字符串数组
     */
    public static String[] splitString(String str, String separator){
        return StringUtils.split(str, separator);
    }
}
