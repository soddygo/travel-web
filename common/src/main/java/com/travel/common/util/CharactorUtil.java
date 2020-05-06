package com.travel.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharactorUtil {

    /**
     * 判断字符串中是否包含中文
     *
     * @param str 待校验字符串
     * @return 是否为中文
     */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * 字符串前端补0到指定位数
     *
     * @param str    字符串
     * @param length 位数
     * @return 处理后的字符串
     */
    public static String fillNumberWithZero(String str, int length) {
        if (str == null) str = "";
        if (str.length() < length) {
            str = StringUtils.repeat('0', length - str.length()) + str;
        }
        return str;
    }

}
