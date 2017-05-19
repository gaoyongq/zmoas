package com.zm.mall.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hh on 2017/5/16.
 */
public  class ConversionUtil {
    //判断字符串是否是数字
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
