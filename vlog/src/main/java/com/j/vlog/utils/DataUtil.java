package com.j.vlog.utils;

import com.j.vlog.model.Card;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName DataUtil
 * @Description
 * @Author orange
 * @Date 2020-12-03 14:26
 **/

public class DataUtil {
    public static List<Card> initCards() {
        Card[] cards = new Card[]{
                Card.builder()
                    .id(1)
                    .title("Java学习")
                    .bgImg("https://share--app.oss-cn-hangzhou.aliyuncs.com/avatar/20201212163842.JPG")
                    .content("Java学习")
                    .build(),
                Card.builder()
                    .id(2)
                    .title("Java Script学习")
                    .bgImg("https://share--app.oss-cn-hangzhou.aliyuncs.com/avatar/20201212163845.JPG")
                    .content("Java Script学习")
                    .build(),
                Card.builder()
                    .id(3)
                    .title("Linux学习")
                    .bgImg("https://share--app.oss-cn-hangzhou.aliyuncs.com/avatar/20201212163847.JPG")
                    .content("Linux学习")
                    .build(),
                Card.builder()
                    .id(4)
                    .title("MySQL学习")
                    .bgImg("https://share--app.oss-cn-hangzhou.aliyuncs.com/avatar/20201212163846.JPG")
                    .content("MySQL学习")
                    .build(),
                Card.builder()
                    .id(5)
                    .title("Python学习")
                    .bgImg("https://share--app.oss-cn-hangzhou.aliyuncs.com/avatar/20201212163850.JPG")
                    .content("Python学习")
                    .build(),
                Card.builder()
                        .id(5)
                        .title("Python学习")
                        .bgImg("https://share--app.oss-cn-hangzhou.aliyuncs.com/avatar/20201212163855.JPG")
                        .content("Python学习")
                        .build(),
                Card.builder()
                        .id(1)
                        .title("Java学习")
                        .bgImg("https://share--app.oss-cn-hangzhou.aliyuncs.com/avatar/20201212163848.JPG")
                        .content("Java学习")
                        .build(),
                Card.builder()
                        .id(1)
                        .title("Java学习")
                        .bgImg("https://share--app.oss-cn-hangzhou.aliyuncs.com/avatar/20201212163852.JPG")
                        .content("Java学习")
                        .build(),
                Card.builder()
                        .id(1)
                        .title("Java学习")
                        .bgImg("https://share--app.oss-cn-hangzhou.aliyuncs.com/avatar/20201212163853.JPG")
                        .content("Java学习")
                        .build(),
        };
        return Arrays.asList(cards);
    }
} 
