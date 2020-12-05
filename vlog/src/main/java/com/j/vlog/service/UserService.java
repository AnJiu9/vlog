package com.j.vlog.service;

import com.j.vlog.model.dto.LoginDto;
import com.j.vlog.model.entity.User;

/**
 * @ClassName UserService
 * @Description 用户service接口
 * @Author orange
 * @Date 5.12.20
 **/

public interface UserService {

    /**
     * 登录
     *
     * @param loginDto
     * @return
     */
    boolean login(LoginDto loginDto);

    /**
     * 根据手机号查找用户
     *
     *
     * @param phone 手机号
     * @return User
     */
    User getUser(String phone);
}
