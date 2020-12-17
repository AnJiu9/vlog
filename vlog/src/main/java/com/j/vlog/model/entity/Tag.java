package com.j.vlog.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Tag
 * @Description 标签实体
 * @Author orange
 * @Date 2020-12-13 21:16
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tag {
    private Integer id;
    private String tagName;
    private String tagColor;
}
