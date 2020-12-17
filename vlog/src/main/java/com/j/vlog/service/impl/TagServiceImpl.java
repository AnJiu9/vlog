package com.j.vlog.service.impl;

import com.j.vlog.mapper.TagMapper;
import com.j.vlog.model.entity.Tag;
import com.j.vlog.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName TagServiceImpl
 * @Description
 * @Author orange
 * @Date 2020-12-13 22:44
 **/

@Service
public class TagServiceImpl implements TagService {
    @Resource
    private TagMapper tagMapper;

    @Override
    public List<Tag> selectAll() {
        return tagMapper.selectAll();
    }
}
