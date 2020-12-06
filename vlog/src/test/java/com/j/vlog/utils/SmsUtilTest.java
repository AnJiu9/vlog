package com.j.vlog.utils;

import com.j.vlog.VlogApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = VlogApplication.class)
@Slf4j
class SmsUtilTest {

    @Resource
    private SmsUtil smsUtil;

    @Test
    void sendSms() {
        smsUtil.sendSms("18805150273","123123");
    }
}