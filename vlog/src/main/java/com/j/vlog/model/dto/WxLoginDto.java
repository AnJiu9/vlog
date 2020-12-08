package com.j.vlog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName WxLoginDto
 * @Description 微信登录dto
 * @Author orange
 * @Date 2020-12-08 19:33
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WxLoginDto {
    private String wxOpenId;
    private String nickname;
    private Integer gender;
    private String avatar;
}
