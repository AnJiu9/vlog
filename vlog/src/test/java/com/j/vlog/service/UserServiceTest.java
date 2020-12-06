package com.j.vlog.service;

import com.j.vlog.VlogApplication;
import com.j.vlog.model.dto.LoginDto;
import com.j.vlog.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

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
}