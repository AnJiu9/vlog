package com.j.vlog.utils;

import com.j.vlog.model.Card;
import org.apache.commons.lang3.RandomUtils;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

    public static String getTotalWords() {
        Random random = new Random();
        int total = random.nextInt(9000) + 1000;
        DecimalFormat df = new DecimalFormat("0.0");
        //'2.6k'的形式，保留一位小数
        return df.format(total / 1000.0) + "k";
    }

    public static int getUserId() {
        Random random = new Random();
        return random.nextInt(3) + 1;
    }

    public static int getDuration() {
        Random random = new Random();
        return random.nextInt(10) + 2;
    }

    public static int getPageView() {
        Random random = new Random();
        //随机四位数
        return random.nextInt(9000) + 1000;
    }

    public static LocalDateTime getRandomLocalDateTime(int startDay, int endDay) {
        int plusMinus = 1;
        if (startDay < 0 && endDay > 0) {
            plusMinus = Math.random() > 0.5 ? 1 : -1;
            if (plusMinus <= 0) {
                endDay = Math.abs(startDay);
            }
            startDay = 0;
        } else if (startDay < 0 && endDay < 0) {
            plusMinus = -1;
            //两个数交换
            startDay = startDay + endDay;
            endDay = startDay - endDay;
            startDay = startDay - endDay;
            //取绝对值
            startDay = Math.abs(startDay);
            endDay = Math.abs(endDay);
        }
        //指定时间
        LocalDate day = LocalDate.now().plusDays(plusMinus * RandomUtils.nextInt(startDay, endDay));
        int hour = RandomUtils.nextInt(1, 24);
        int minute = RandomUtils.nextInt(0, 60);
        int second = RandomUtils.nextInt(0, 60);
        LocalTime time = LocalTime.of(hour, minute, second);
        return LocalDateTime.of(day, time);
    }
} 
