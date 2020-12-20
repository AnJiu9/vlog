package com.j.vlog.mapper;


import com.j.vlog.model.entity.Article;
import org.apache.ibatis.annotations.*;

import com.github.pagehelper.Page;

import java.util.List;

/**
 * @ClassName ArticleMapper
 * @Description
 * @Author orange
 * @Date 2020-12-15 15:18
 **/

public interface ArticleMapper {

    /**
     * 新增文章
     *
     * @param article 入参
     * @return int
     */
    @Insert("INSERT INTO t_article(id,category,user_id,title,cover,summary,content,publish_date,total_words,duration,page_view)" +
            "VALUES (#{article.id},#{article.category},#{article.userId},#{article.title},#{article.cover},#{article.summary},#{article.content}," +
            "#{article.publishDate},#{article.totalWords},#{article.duration},#{article.pageView})")
    int add(@Param("article") Article article);

    /**
     * 批量插入文章
     *
     * @param articleList 文章集合
     * @return int
     */
    @Insert({
            "<script>",
            "INSERT INTO t_article (id,category,user_id,title,cover,summary,content,url,publish_date,total_words,duration,page_view) VALUES ",
            "<foreach collection='articleList' item='item' index='index' separator=','>",
            "(#{item.id},#{item.category},#{item.userId},#{item.title},#{item.cover},#{item.summary},#{item.content},"+
                    "#{item.url},#{item.publishDate},#{item.totalWords},#{item.duration},#{item.pageView})",
            "</foreach>",
            "</script>"
    })
    int insertArticles(@Param(value = "articleList")List<Article> articleList);

    /**
     * 查询某个用户的6篇推荐文章
     *
     * @param userId 用户ID
     * @return List<Article>
     */
    @Select("SELECT id,category,user_id,title,cover,summary FROM t_article WHERE user_id = #{userId} ORDER BY page_view DESC LIMIT 6 ")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "category", column = "category"),
            @Result(property = "userId", column = "user_Id"),
            @Result(property = "title", column = "title"),
            @Result(property = "cover", column = "cover"),
            @Result(property = "summary", column = "summary")
    })
    List<Article> getRecommendArticles(@Param(value = "userId") int userId);

    /**
     * 查询用户的所有文章，一对多关联查询，"一方"（文章）需要存放"多方"（文章标签）的集合，双向一对多就是多对多
     * 最后一行SQL很重要，其中property是Article中的定义的"多方"集合变量
     * column是文章表中的文章id，也就是一对多查询的依据
     * many为在"多方"mapper中的查询方法（务必对应）
     *
     *
     * @param userId 用户id
     * @return 文章集合
     */
    @Select("SELECT id,category,user_id,title,cover,summary,publish_date FROM t_article a WHERE a.user_id = #{userId} ORDER BY publish_date DESC")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "category", column = "category"),
            @Result(property = "userId", column = "user_Id"),
            @Result(property = "title", column = "title"),
            @Result(property = "cover", column = "cover"),
            @Result(property = "summary", column = "summary"),
            @Result(property = "publishDate", column = "publish_date"),
            @Result(property = "tagList", column = "id",
                many = @Many(select = "com.j.vlog.mapper.ArticleTagMapper.selectByArticleId"))
    })
    Page<Article> selectAll(@Param(value = "userId") int userId);


    /**
     * 根据文章id查找文章详情
     *
     * @param id 文章id
     * @return Article详情
     */
    @Select("SELECT * FROM t_article WHERE id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "category", column = "category"),
            @Result(property = "userId", column = "user_Id"),
            @Result(property = "title", column = "title"),
            @Result(property = "cover", column = "cover"),
            @Result(property = "content", column = "content"),
            @Result(property = "publishDate", column = "publish_date"),
            @Result(property = "totalWords", column = "total_words"),
            @Result(property = "duration", column = "duration"),
            @Result(property = "pageView", column = "page_view"),
            @Result(property = "tagList", column = "id",
                    many = @Many(select = "com.j.vlog.mapper.ArticleTagMapper.selectByArticleId"))
    })
    Article getDetail(@Param(value = "id") String id);
}
