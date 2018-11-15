package com.tony.test.httpipv2.controller;

import com.tony.test.httpipv2.redis.client.RedisKeyAPI;
import com.tony.test.httpipv2.redis.key.AccessKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.tony.test.httpipv2.redis.key.HelloKey.HELLO;

@RestController
public class HelloController {

    @Autowired
    RedisKeyAPI redisKey;

    @RequestMapping("/hello")
    public String hello() {
        System.out.println("hello2");
        return "hello2";
    }

    @RequestMapping("/redis")
    public Object redis(int expire) {
        redisKey.set(HELLO, redisKey.getClass().getCanonicalName());

//        AccessKey key = AccessKey.withExpire(expire, "/redis");
//        Integer count = (Integer) redis.get(key.getPrefix() + key.getKey());
//        if (count == null) {
//            redis.setex(key.getPrefix() + key.getKey(), 1, key.expireSeconds(), TimeUnit.SECONDS);
//        } else if (count < 2) {
//            redis.incr(key.getPrefix() + key.getKey());
//        } else {
//            return "访问太频繁";
//        }

        AccessKey key = AccessKey.withExpire(expire, "/redis");
        Integer count = (Integer) redisKey.get(key);
        if (count == null) {
            redisKey.setex(key, 1);
        } else if (count < 2) {
            redisKey.incr(key);
        } else {
            return "访问太频繁";
        }

        return redisKey.get(HELLO);
    }

}
