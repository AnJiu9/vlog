package com.j.vlog.service.impl;

import com.j.vlog.mapper.CommentMapper;
import com.j.vlog.model.entity.Comment;
import com.j.vlog.model.vo.CommentVo;
import com.j.vlog.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName CommentServiceImpl
 * @Description
 * @Author orange
 * @Date 2020-12-22 14:33
 **/
@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentMapper commentMapper;

    @Override
    public List<CommentVo> selectByArticleId(String articleId) {
        return commentMapper.selectByArticleId(articleId);
    }

    @Override
    public List<CommentVo> addComment(Comment comment) {
        //补充评论时间
        comment.setCreateTime(LocalDateTime.now());
        //插入
        commentMapper.addComment(comment);
        //查出这篇文章的最新的评论列表
        return commentMapper.selectByArticleId(comment.getArticleId());
    }
}
