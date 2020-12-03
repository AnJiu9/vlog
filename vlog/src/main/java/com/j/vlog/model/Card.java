package com.j.vlog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Card
 * @Description 卡片类
 * @Author orange
 * @Date 2020-12-03 14:19
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Card {
    private Integer id;
    private String title;
    private String bgImg;
    private String content;
}
