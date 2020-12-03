package com.j.vlog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloControlle
 * @Description
 * @Author orange
 * @Date 2020-12-03 12:41
 **/


@RestController
@RequestMapping(value = "api")

public class HelloController {

    @GetMapping("hello")
    public String hello() {
        return "hello spring boot";
    }
}


