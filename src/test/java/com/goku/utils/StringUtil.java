package com.goku.utils;

import org.apache.commons.lang3.SystemUtils;

import java.io.UnsupportedEncodingException;

public class StringUtil {
    public static String convertUtf8(String win1252) throws UnsupportedEncodingException {
        return SystemUtils.IS_OS_LINUX ? new String(win1252.getBytes("Windows-1252"), "UTF-8") : win1252;
    }
}
