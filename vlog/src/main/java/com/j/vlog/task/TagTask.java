package com.j.vlog.task;

import com.j.vlog.model.entity.Tag;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.TagName;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName TagTask
 * @Description 爬取Tag数据的任务线程
 * @Author orange
 * @Date 2020-12-13 21:20
 **/

@Component
@Slf4j
public class TagTask implements Callable<List<Tag>> {
    @Override
    public List<Tag> call() throws Exception {
//        System.out.println("call方法执行");
//        return Tag.builder().id(111).tagName("测试标签").tagColor("#FFFFFF").build();
        //页面文档对象
        Document document = null;
        List<Tag> tagList = new ArrayList<>(100);
        try {
            //与目标页面建立连接
            document = Jsoup.connect("https://godweiyang.com/tags/").get();
        } catch (IOException e) {
            log.error("连接失败");
        }
        assert document != null;
        //取得包含了所有标签的div，因为是根据class寻找，所以返回值是个集合
        Elements tagChips = document.getElementsByClass("tag-chips");
        //需要get(0)，然后再获取其所有孩子结点，也就是所有的a标签
        Elements tags = tagChips.get(0).children();
        //遍历所有a标记
        tags.forEach(tagNode -> {
            //通过取结点属性的方法， 得到"博客：4"这样的结果
            String title = tagNode.attr("title");
            //分割一下得到：之前的内容
            title = title.split(":")[0];
            //得到a的第一个孩子结点，也就是span元素，从中获得颜色
            Element span = tagNode.child(0);
            //得到"background-color：#F9EBEA；"这样的结果
            String styleContent = span.attr("style");
            //再取子串得到颜色的值: #F9EBEA
            String color = styleContent.substring(styleContent.indexOf("#"), styleContent.length()-1);
            //构建Tag对象
            Tag tag = Tag.builder().tagName(title).tagColor(color).build();
            //加入结果集合
            tagList.add(tag);
        });
        return tagList;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException{
        ExecutorService executor = Executors.newFixedThreadPool(2);
        TagTask tt = new TagTask();
        Future<List<Tag>> future = executor.submit(tt);
        List<Tag> tags = future.get();
        tags.forEach(tag -> System.out.println(tag.getTagName() + "," + tag.getTagColor()));

    }
}
