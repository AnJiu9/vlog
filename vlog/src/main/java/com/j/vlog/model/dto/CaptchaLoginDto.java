package com.j.vlog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName CaptchaLoginDto
 * @Description 验证码登录Dto
 * @Author orange
 * @Date 2020-12-10 22:07
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CaptchaLoginDto {
    private String phone;
    private String password;
    private String captcha;
}

