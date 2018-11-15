package com.tony.test.httpipv2.redis.key;

public class HelloKey extends BasePrefix {

    public HelloKey(String prefix, String key) {
        super(prefix, key);
    }

    public HelloKey(int expireSeconds, String prefix, String key) {
        super(expireSeconds, prefix, key);
    }

    public static HelloKey HELLO = new HelloKey("", "hello");
}
