package com.j.vlog.task;

import com.j.vlog.model.entity.Article;
import com.j.vlog.model.entity.ArticleTag;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * @ClassName ArticleTask
 * @Description 文章爬虫
 * @Author orange
 * @Date 2020-12-15 14:05
 **/

@Component
@Slf4j
public class ArticleTask implements Callable<List<Article>> {
    private static final String BASE_URL = "https://godweiyang.com";
    private Document document = null;
    private List<Article> articleList;

    @Override
    public List<Article> call() throws Exception {
        articleList = new ArrayList<>(100);
        //页码
        int index;
        //抓取首页文章推荐文章数据
        try {
            document = Jsoup.connect(BASE_URL).get();
        } catch (IOException e) {
            log.error("连接失败");
        }
        assert document != null;
        Element indexCard = document.getElementById("indexCard");
        //推荐的6篇文章结点
        Elements recommend = indexCard.select(".col");
        recommend.forEach(articleNode -> {
            //id
            String id = UUID.randomUUID().toString();
            //分类
            Element categoryNode = articleNode.select(".category").get(0);
            String category = categoryNode.html();
            //标题
            Element titleNode = articleNode.select(".post-title").get(0);
            String title = titleNode.text();
            //摘要
            Element summaryNode = articleNode.select(".post-description").get(0);
            String summary = summaryNode.text();
            //url
            Element urlNode = articleNode.select(".read-more").get(0);
            String url = BASE_URL + urlNode.attr("href");
            //内容
            String content = getContent(url);
            Article article = Article.builder()
                    .id(id)
                    .userId(1)
                    .title(title)
                    .category(category)
                    .cover("https://picsum.photos/1920/1080?random&rand=" + Math.random())
                    .summary(summary)
                    .content(content)
                    .url(url)
                    .build();
            articleList.add(article);
        });

            //抓取第2页后面的文章数据
        for (index = 2; index < 19; index++) {
            try {
                //与目标页面建立连接
                document = Jsoup.connect(BASE_URL + "/page/" + index).get();
            } catch (IOException e) {
                log.error("连接失败");
            }
            assert document != null;
            Elements articles2 = document.getElementsByClass("card");
            //调用封装的方法，来解析这些文章
            parseArticles(articles2);
        }
        return articleList;
    }

    private String getContent(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            log.error("连接失败");
        }
        assert document != null;
        Element content = document.getElementById("articleContent");
        return content.html();
    }

    private void parseArticles(Elements articles) {
        for (Element articleNode : articles) {
            //生成文章id
            String id = UUID.randomUUID().toString();
            //标题
            Element titleSpan = articleNode.select(".card-title").get(0);
            String title = titleSpan.text();
            //封面图，用随机图片代替l
//            Element imgNode = articleNode.select(".responsive-img").get(0);
//            String cover = BASE_URL + imgNode.attr("src");
            //分类
            Element categoryNode = articleNode.select(".post-category").get(0);
            String category = categoryNode.html();
            //摘要
            Element summaryNode = articleNode.select(".summary").get(0);
            String summary = summaryNode.html();
            //文章链接
            String url = BASE_URL + articleNode.child(0).attr("href");
            //发表时间
            Element dateNode = articleNode.select(".publish-date").get(0);
            String publishDateString = dateNode.text();
            //将字符串日期转成LocalDate
            LocalDate publishDate = LocalDate.parse(publishDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            //标签
            List<ArticleTag> articleTags = new ArrayList<>();
            Elements tagNodes = articleNode.select(".article-tags").get(0).getElementsByTag("a");
            for (Element tagNode : tagNodes) {
                ArticleTag articleTag = ArticleTag.builder()
                        .articleId(id)
                        .tagName(tagNode.child(0).text())
                        .build();
                articleTags.add(articleTag);
            }
            //文章内容，需要根据文章的url再次打开文章详情页面爬取，封装一个方法调用
            String content = getContent(url);

            Article article = Article.builder()
                    .id(id)
                    .userId(1)
                    .title(title)
                    .category(category)
                    .cover("https://picsum.photos/1920/1080?random&rand=" + Math.random())
                    .summary(summary)
                    .content(content)
                    .url(url)
                    .publishDate(publishDate)
                    .tagList(articleTags)
                    .build();
            articleList.add(article);
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        ArticleTask at = new ArticleTask();
        Future<List<Article>> future = executor.submit(at);
        List<Article> articles = future.get();
        articles.forEach(article -> System.out.println(article.getTitle() + "," + article.getCategory()));
    }
}
