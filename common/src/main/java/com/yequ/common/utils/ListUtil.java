package com.yequ.common.utils;

import java.util.List;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-08
 **/
public class ListUtil {
    public static boolean isEmpty(List ls){

        if(null == ls){
            return true;
        }
        if(ls.size()==0){
            return true;
        }
        return false;
    }
}
