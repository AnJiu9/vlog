package com.j.vlog.mapper;

import com.j.vlog.VlogApplication;
import com.j.vlog.common.Gender;
import com.j.vlog.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = VlogApplication.class)
@Slf4j
class UserMapperTest {
    //注入UserMapper的实例
    @Resource
    private UserMapper userMapper;

    @Test
    void insert() throws Exception{
        User user = User.builder()
                .phone("18796428166")
                .password("123123")
                .nickname("Test")
                .avatar("1.jpg")
                .gender(Gender.female.type)
                .birthday(LocalDate.now())
                .address("江苏南京")
                .createTime(LocalDateTime.now())
                .build();
        userMapper.insert(user);
    }

    @Test
    void findUserByPhone() throws Exception{
        User user = userMapper.findUserByPhone("18805150273");
        log.info(String.valueOf(user));
    }
}