package com.j.vlog.mapper;

import com.github.pagehelper.Page;
import com.j.vlog.VlogApplication;
import com.j.vlog.model.entity.Article;
import com.j.vlog.model.vo.ArticleVo;
import com.j.vlog.task.ArticleTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = VlogApplication.class)
@Slf4j
class ArticleMapperTest {
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ArticleTask articleTask;

    @Test
    void insertArticles() throws Exception{
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 8, 5,
                TimeUnit.SECONDS, new SynchronousQueue<>());
        Future<List<Article>> future = executor.submit(articleTask);
        List<Article> articles = future.get();
        int count = articleMapper.insertArticles(articles);
        System.out.println(count);

    }

    @Test
    void selectAll() {
        Page<ArticleVo> articlePage = articleMapper.selectAll();
        System.out.println(articlePage.toPageInfo().getList().size());
    }

    @Test
    void getRecommendArticles(){
        List<ArticleVo> articles = articleMapper.getRecommendArticles();
        System.out.println(articles);
    }

    @Test
    void getDetail() {
        ArticleVo article = articleMapper.getDetail("00f4ab8f109f45ea804581beca76af21");
        System.out.println(article);
    }
}