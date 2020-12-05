package com.j.vlog.common;

import sun.net.idn.Punycode;

/**
 * @ClassName Gender
 * @Description 性别枚举
 * @Author orange
 * @Date 2020-12-05 11:58
 **/

public enum  Gender {
    /**
     * 枚举
     */
    male(1,"男"),
    female(2,"女"),
    secret(0,"保密");

    public final Integer type;
    public final String value;

    Gender(Integer type,String value){
        this.type = type;
        this.value = value;
    }
}
