package com.j.vlog.mapper;

import com.github.pagehelper.Page;
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
        Page<Article> articlePage = articleMapper.selectAll(1);
        System.out.println(articlePage.toPageInfo().getList().size());
    }
}