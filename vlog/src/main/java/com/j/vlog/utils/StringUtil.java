package com.j.vlog.utils;

import java.util.Random;

/**
 * @ClassName StringUtil
 * @Description
 * @Author orange
 * @Date 2020-12-06 14:06
 **/

public class StringUtil {
    public static String getVerifyCode(){
        Random random=new Random();
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<6;i++){
            stringBuilder.append(String.valueOf(random.nextInt(10)));
        }
        return stringBuilder.toString();
    }
}
