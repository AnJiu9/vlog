package com.j.vlog.service;

import com.j.vlog.VlogApplication;
import com.j.vlog.common.Gender;
import com.j.vlog.model.dto.LoginDto;
import com.j.vlog.model.entity.User;
import com.sun.tools.javah.Gen;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = VlogApplication.class)
@Slf4j
class UserServiceTest {
    //注入UserService 实例
    @Resource
    private UserService userService;

    @Test
    void login() {
        LoginDto loginDto = LoginDto.builder()
                .phone("18805150273")
                .password("123123")
                .build();
        boolean flag = userService.login(loginDto);
        assertTrue(flag);
    }


    @Test
    void getUser() {
        User user = userService.getUser("18805150273");
        log.info(String.valueOf(user));
    }

    @Test
    void updateUser() {
        User user = User.builder()
                .phone("18796428166")
                .password(DigestUtils.md5Hex("123123"))
                .nickname("jyx")
                .avatar("https://share--app.oss-cn-hangzhou.aliyuncs.com/avatar/20201205181922.jpg")
                .gender(Gender.secret.type)
                .birthday(LocalDate.of(1999,5,8))
                .address("江苏苏州")
                .build();
        userService.updateUser(user);
    }
}