package com.j.vlog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName PhoneLoginDto
 * @Description 手机短信登录dto
 * @Author orange
 * @Date 6.12.20
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneLoginDto {
    private String phone;
    private String code;
}
