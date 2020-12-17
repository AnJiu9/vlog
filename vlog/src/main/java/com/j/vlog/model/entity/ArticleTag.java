package com.j.vlog.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ArticleTag
 * @Description
 * @Author orange
 * @Date 2020-12-15 14:03
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleTag {
    private Integer id;
    private String articleId;
    private String tagName;
}
