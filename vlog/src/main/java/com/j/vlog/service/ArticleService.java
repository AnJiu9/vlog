package com.j.vlog.service;

import com.github.pagehelper.PageInfo;
import com.j.vlog.model.entity.Article;

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
     * 根据用户id查询数据并分页
     *
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @param userId 用户id
     * @return 返回结果
     */
    PageInfo<Article> selectByPage(int pageNum, int pageSize, int userId);
}
