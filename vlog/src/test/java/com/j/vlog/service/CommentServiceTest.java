package com.j.vlog.service;

import com.j.vlog.VlogApplication;
import com.j.vlog.model.entity.Comment;
import com.j.vlog.model.vo.CommentVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.annotation.Resource;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = VlogApplication.class)
@Slf4j
class CommentServiceTest {
    @Resource
    private CommentService commentService;

    @Test
    void selectByArticleId() {
        List<CommentVo> commentVos = commentService.selectByArticleId("01b0079bc35a412f89c697da19d6e083");
        commentVos.forEach(System.out::println);
    }

    @Test
    void addComment() {
        Comment comment = Comment.builder()
                .articleId("01b0079bc35a412f89c697da19d6e083")
                .userId(1)
                .content("AnJiu的评论")
                .createTime(LocalDateTime.now())
                .build();
        List<CommentVo> commentVos = commentService.addComment(comment);
        commentVos.forEach(System.out::println);
    }
}