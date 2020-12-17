package com.j.vlog.mapper;

import com.j.vlog.VlogApplication;
import com.j.vlog.model.entity.Tag;
import com.j.vlog.task.TagTask;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = VlogApplication.class)
class TagMapperTest {
    @Resource
    private TagTask tagTask;

    @Resource
    private TagMapper tagMapper;

    @Test
    void batchInsert() throws Exception {
        //线程池核心线程数为6，最大线程为10，超时时间为5秒
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 8, 5, TimeUnit.SECONDS, new SynchronousQueue<>());
        Future<List<Tag>> future = executor.submit(tagTask);
        List<Tag> tags = future.get();
        int count = tagMapper.insertTags(tags);
        System.out.println(count);
    }

    @Test
    void selectAll() {
        List<Tag> tags = tagMapper.selectAll();
        tags.forEach(tag -> System.out.println(tag.getTagName() + "," + tag.getTagColor()));
    }
}