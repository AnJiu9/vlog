package com.j.vlog.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @ClassName Comment
 * @Description 评论实体
 * @Author orange
 * @Date 2020-12-22 14:08
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    private Integer id;
    private String articleId;
    private Integer userId;
    private String content;
    private LocalDateTime createTime;
}
