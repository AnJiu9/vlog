package com.j.vlog.mapper;

import com.j.vlog.VlogApplication;
import com.j.vlog.model.entity.Comment;
import com.j.vlog.model.vo.CommentVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = VlogApplication.class)
@Slf4j
class CommentMapperTest {
    @Resource
    private CommentMapper commentMapper;

    @Test
    void selectByArticleId() {
        List<CommentVo> commentVos = commentMapper.selectByArticleId("0175ea5c94c447dda5652eae0a2c9dfa");
        commentVos.forEach(commentVo -> System.out.println(commentVos));
    }

    @Test
    void addComment() {
        Comment comment = Comment.builder()
                .articleId("0175ea5c94c447dda5652eae0a2c9dfa")
                .userId(1)
                .content("测试评论")
                .createTime(LocalDateTime.now())
                .build();
        commentMapper.addComment(comment);
    }
}