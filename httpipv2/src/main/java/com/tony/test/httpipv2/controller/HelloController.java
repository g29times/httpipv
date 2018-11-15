package com.tony.test.httpipv2.controller;

import com.tony.test.httpipv2.redis.RedisAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    RedisAPI redis;

    @RequestMapping("/hello")
    public String hello() {
        System.out.println("hello2");
        return "hello2";
    }

    @RequestMapping("/redis")
    public Object redis() {
        redis.set("hello", redis.getClass().getCanonicalName());
        return redis.get("hello");
    }

}
