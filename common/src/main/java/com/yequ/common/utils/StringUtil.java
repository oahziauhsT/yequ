package com.yequ.common.utils;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-01
 **/
public class StringUtil {

    public static boolean isEmpty(Object object){
        if(null == object){
            return true;
        }
        if(object.toString().isEmpty() || object.toString().trim().isEmpty()){
            return true;
        }
        return false;
    }
}
