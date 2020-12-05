package com.j.vlog.service.impl;

import com.j.vlog.mapper.UserMapper;
import com.j.vlog.model.dto.LoginDto;
import com.j.vlog.model.entity.User;
import com.j.vlog.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.ws.ServiceMode;
import java.sql.SQLException;

/**
 * @ClassName UserServiceImpl
 * @Description
 * @Author orange
 * @Date 2020-12-05 13:08
 **/
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public boolean login(LoginDto loginDto) {
        User user = getUser(loginDto.getPhone());
        if (user != null){
            return DigestUtils.md5Hex(loginDto.getPassword()).equals(user.getPassword());
        }
        return false;
    }

    @Override
    public User getUser(String phone) {
        User user = null;
        try{
            user = userMapper.findUserByPhone(phone);
        } catch (SQLException throwables){
            System.err.println("根据手机号查找用户出现异常");
        }
        return user;
    }
}
