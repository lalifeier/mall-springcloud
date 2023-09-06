package com.github.lalifeier.mall.cloud.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExUtil {

    public static Boolean match(String rex, String str) {
        Pattern pattern = Pattern.compile(rex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
