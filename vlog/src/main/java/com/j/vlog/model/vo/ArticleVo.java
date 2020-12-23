package com.j.vlog.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.j.vlog.model.entity.ArticleTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName ArticleVo
 * @Description 文章视图类
 * @Author orange
 * @Date 2020-12-21 17:53
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleVo {
    private String id;
    private String category;
    private Integer userId;
    private String title;
    private String cover;
    private String summary;
    private String content;
    private String url;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime createTime;
    private String totalWords;
    private String duration;
    private String pageView;
    private List<ArticleTag> tagList;
    private List<CommentVo> commentList;
    private String nickname;
    private String avatar;
}
