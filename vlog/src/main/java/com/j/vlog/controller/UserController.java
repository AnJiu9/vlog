package com.j.vlog.controller;

import com.j.vlog.common.ResponseResult;
import com.j.vlog.common.ResultCode;
import com.j.vlog.model.dto.CaptchaLoginDto;
import com.j.vlog.model.dto.LoginDto;
import com.j.vlog.model.dto.PhoneLoginDto;
import com.j.vlog.model.dto.WxLoginDto;
import com.j.vlog.model.entity.User;
import com.j.vlog.service.RedisService;
import com.j.vlog.service.UserService;
import com.j.vlog.utils.FileResource;
import com.j.vlog.utils.SmsUtil;
import com.j.vlog.utils.StringUtil;
import com.sun.source.tree.ReturnTree;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.sql.SQLOutput;
import java.util.Random;

/**
 * @ClassName UserController
 * @Description 用户Controller
 * @Author orange
 * @Date 2020-12-05 15:05
 **/

@RestController
@RequestMapping(value = "api/user")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private SmsUtil smsUtil;
    @Resource
    private RedisService redisService;
    @Resource
    private FileResource fileResource;


    @PostMapping(value = "/login")
    ResponseResult login(@RequestBody LoginDto loginDto) {
        log.info("loginDto:" + loginDto);
        boolean flag = userService.login(loginDto);
        if (flag) {
            return ResponseResult.success(userService.getUser(loginDto.getPhone()));
        } else {
            return ResponseResult.failure(ResultCode.USER_SIGN_IN_FAIL);
        }
    }

    @PostMapping(value = "/sendcode")
    public ResponseResult sendCode(@RequestParam String phone) {
        //随机验证码
        String code = StringUtil.getVerifyCode();
        //给入参手机号发送短信
        boolean flag = smsUtil.sendSms(phone, code);
        if (flag){
            //验证码存入redis，5分钟有效
            redisService.set(phone, code, 1L);
            return ResponseResult.success(code);
        }else {
            redisService.set(phone, code, 1L);
            return ResponseResult.failure(ResultCode.SMS_ERROR);
        }
    }

    @PostMapping(value = "/phonelogin")
    public ResponseResult login(@RequestBody PhoneLoginDto phoneLoginDto) {
        log.info("phoneLoginDto:" + phoneLoginDto);
        boolean flag = userService.phoneLogin(phoneLoginDto);
        if (flag) {
            return ResponseResult.success(userService.getUser(phoneLoginDto.getPhone()));
        } else {
            return ResponseResult.failure(ResultCode.USER_VERIFY_CODE_ERROR);
        }
    }

    @PostMapping(value = "/update")
    public ResponseResult update(@RequestBody User user) {
        log.info("user:" + user);
        User newUser = userService.updateUser(user);
        return ResponseResult.success(newUser);
    }

    @PostMapping(value = "/upload")
    public ResponseResult uploadFile(MultipartFile file) {
        //声明图片的地址路径，返回到前端
        String path = null;
        //判断文件不能为空
        if (file != null) {
            //获得文件上传名称
            String fileName = file.getOriginalFilename();
            System.out.println(fileName);
            //调用上传服务器，得到上传后的新文件名
            path = userService.uploadFile(file);
        }
        if (StringUtils.isNotBlank(path)) {
            //拼接上服务器地址前缀，得到最终返回给前端的url
            path = fileResource.getOssHost() + path;
        }
        return ResponseResult.success(path);
    }

    @PostMapping(value = "/wxLogin")
    public ResponseResult wxLogin(@RequestBody WxLoginDto wxLoginDto) {
        log.info("wxLoginDto:" + wxLoginDto);
        User user = userService.wxLogin(wxLoginDto);
        if (user != null) {
            return ResponseResult.success(user);
        }
        return ResponseResult.failure(ResultCode.USER_SIGN_IN_FAIL);
    }

    @PostMapping(value = "/captchaLogin")
    public ResponseResult captchaLogin(@RequestBody CaptchaLoginDto captchaLoginDto) {
        log.info("captchaLoginDto:" + captchaLoginDto);
        User user = userService.captchaLogin(captchaLoginDto);
        if (user != null) {
            return ResponseResult.success(user);
        }
        return ResponseResult.failure(ResultCode.USER_SIGN_IN_FAIL);
    }
}
