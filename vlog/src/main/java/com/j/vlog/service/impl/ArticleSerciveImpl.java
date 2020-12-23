package com.j.vlog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.j.vlog.mapper.ArticleMapper;
import com.j.vlog.mapper.ArticleTagMapper;
import com.j.vlog.model.entity.Article;
import com.j.vlog.model.entity.ArticleTag;
import com.j.vlog.model.vo.ArticleVo;
import com.j.vlog.service.ArticleService;
import com.j.vlog.utils.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName ArticleSerciveImpl
 * @Description
 * @Author orange
 * @Date 2020-12-15 15:55
 **/

@Component
@Slf4j
public class ArticleSerciveImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ArticleTagMapper articleTagMapper;

    @Override
    public void insertArticles(List<Article> articles) {
        articleMapper.insertArticles(articles);
        assert articles != null;
        articles.forEach(article -> {
            if (article.getTagList() != null) {
                articleTagMapper.insertArticleTags(article.getTagList());
            }
        });
    }

    @Override
    public List<ArticleVo> getRecommendArticles() {
        return articleMapper.getRecommendArticles();
    }

    @Override
    public PageInfo<ArticleVo> selectByPage(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页
        PageHelper.startPage(pageNum, pageSize);
        //查到所有数据
        Page<ArticleVo> articlePage = articleMapper.selectAll();
        //将这些数据作为入参构建出PageInfo（包含了总页数，当前页码、每页数量、当前页数据List等一堆属性和方法）
        return new PageInfo<ArticleVo>(articlePage);
    }

    @Override
    public ArticleVo getDetail(String id) {
        return articleMapper.getDetail(id);
    }

    @Override
    public Article postArticle(Article article) {
        article.setCover("https://picsum.photos/1920/1080?random&rand=" + Math.random());
        article.setCreateTime(DataUtil.getRandomLocalDateTime(-1000,1));
        article.setTotalWords(DataUtil.getTotalWords());
        article.setDuration(DataUtil.getDuration());
        article.setPageView(DataUtil.getPageView());
        System.out.println(article);
        //新增文章
        articleMapper.add(article);
        //获得文章的标签列表
        List<ArticleTag> tagList = article.getTagList();
        //批量插入标签
        articleTagMapper.insertArticleTags(tagList);
        return article;
    }
}
