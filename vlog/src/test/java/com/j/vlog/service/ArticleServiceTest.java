package com.j.vlog.service;

import com.github.pagehelper.PageInfo;
import com.j.vlog.VlogApplication;
import com.j.vlog.model.entity.Article;
import com.j.vlog.task.ArticleTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = VlogApplication.class)
@Slf4j
class ArticleServiceTest {
    @Resource
    private ArticleService articleService;
    @Resource
    private ArticleTask articleTask;

    @Test
    void insertArticles() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4,8,5,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        Future<List<Article>> future = executor.submit(articleTask);
        List<Article> articles = null;
        try {
            articles = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        articleService.insertArticles(articles);
    }

    @Test
    void selectByPage() {
        PageInfo<Article> articlePageInfo = articleService.selectByPage(1,9,1);
        System.out.println(articlePageInfo.getList().size());
    }
}