package com.j.vlog.controller;

import com.github.pagehelper.PageInfo;
import com.j.vlog.common.ResponseResult;
import com.j.vlog.model.entity.Article;
import com.j.vlog.model.vo.ArticleVo;
import com.j.vlog.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName ArticleController
 * @Description
 * @Author orange
 * @Date 2020-12-15 16:20
 **/

@RestController
@RequestMapping(value = "/api/article")
@Slf4j
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @PostMapping("page")
    public PageInfo<ArticleVo> getArticlesByPage(@RequestParam(name = "pageNum", defaultValue = "1", required = false) int pageNum,
                                               @RequestParam(name = "pageSize", defaultValue = "9", required = false) int pageSize) {
        PageInfo<ArticleVo> articlePageInfo = articleService.selectByPage(pageNum,pageSize);
        if (articlePageInfo == null) {
            throw new NullPointerException();
        }
        return articlePageInfo;
    }

    @GetMapping("recommend")
    public List<ArticleVo> getRecommend() {
        List<ArticleVo> recommendArticles = articleService.getRecommendArticles();
        if (recommendArticles == null) {
            throw new NullPointerException();
        }
        return recommendArticles;
    }

    @GetMapping("{id}")
    public ArticleVo getArticleDetail(@PathVariable String id) {
        ArticleVo detail = articleService.getDetail(id);
        if (detail == null) {
            throw new NullPointerException();
        }
        return detail;
    }

    public int getUserId(){
        //通过RequestContextHolder来取得请求的request对象
        ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();;
        assert sra != null;
        HttpServletRequest request = sra.getRequest();
       return Integer.parseInt(request.getHeader("userId"));
    }

    @PostMapping("post")
    public Article postArticle(@RequestBody Article article) {
        return articleService.postArticle(article);
    }
}
