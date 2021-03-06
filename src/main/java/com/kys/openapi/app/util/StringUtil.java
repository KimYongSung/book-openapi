package com.kys.openapi.app.util;

import java.util.Objects;
import java.util.regex.Pattern;

public class StringUtil {

    public static final String EMPTY = "";

    /**
     * 문자열 NULL or Empty 여부
     * @param str
     * @return null이거나 공백이면 true
     */
    public static boolean isNullOrEmpty(String str) {
        return Objects.isNull(str) || str.isEmpty();
    }

    public static String requireNonNullOrNonEmpty(String str, String message) {
        if (isNullOrEmpty(str))
            throw new NullPointerException(message);
        return str;
    }

    /**
     * 문자열이 NULL or Empty 여부
     * @param str
     * @return null이거나 공백이면 false
     */
    public static boolean nonNullOrNonEmpty(String str){
        return !isNullOrEmpty(str);
    }
    /**
     * 정수 여부 확인
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        if (isNullOrEmpty(str))
            return false;
        return Pattern.matches("[0-9]+", str);
    }
}
