package com.j.vlog.service;

import com.github.pagehelper.PageInfo;
import com.j.vlog.model.entity.Article;
import com.j.vlog.model.vo.ArticleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName ArticleService
 * @Description TODO
 * @Author orange
 * @Date 15.12.20
 **/

public interface ArticleService {

    /**
     * 批量新增文章
     *
     * @param articles 文章集合
     */
    void insertArticles(List<Article> articles);

    /**
     * 查询推荐的6篇文章
     *
     */
    List<ArticleVo> getRecommendArticles();

    /**
     * 根据用户id查询数据并分页
     *
     * @param pageNum 页码
     * @param pageSize 每页数量
//     * @param userId 用户id
     * @return 返回结果
     */
    PageInfo<ArticleVo> selectByPage(int pageNum, int pageSize);

    /**
     * 根据文章id查找文章详情
     *
     * @param id 文章id
     * @return Article详情
     */
    ArticleVo getDetail(@Param(value = "id") String id);

    /**
     * 发布文章
     *
     * @param article 文章
     * @return article
     */
    Article postArticle(Article article);
}
