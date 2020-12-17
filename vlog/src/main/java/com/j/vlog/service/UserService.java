package com.j.vlog.service;

import com.j.vlog.model.dto.CaptchaLoginDto;
import com.j.vlog.model.dto.LoginDto;
import com.j.vlog.model.dto.PhoneLoginDto;
import com.j.vlog.model.dto.WxLoginDto;
import com.j.vlog.model.entity.User;
import org.omg.CORBA.StringHolder;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    User updateUser(User user);


    /**
     * 手机短信验证码登录
     *
     * @param phoneLoginDto 入参
     * @return boolean
     */
    boolean phoneLogin(PhoneLoginDto phoneLoginDto);

    /**
     * 上传文件到OSS
     *
     * @param file 文件对象
     * @return 上传后的url
     */
    String uploadFile(MultipartFile file);

    /**
     * 微信登录
     *
     * @param wxLoginDto 入参
     * @return boolean
     */
    User wxLogin(WxLoginDto wxLoginDto);

    /**
     * 验证码登录
     *
     * @param captchaLoginDto 入参
     * @return User
     */
    User captchaLogin(CaptchaLoginDto captchaLoginDto);
}
