package com.j.vlog.service;

import com.j.vlog.model.entity.Tag;

import java.util.List;

/**
 * @ClassName TagService
 * @Description TODO
 * @Author orange
 * @Date 13.12.20
 **/

public interface TagService {

    /**
     * 查询所有标签
     *
     * @return 所有标签
     */
    List<Tag> selectAll();
}
