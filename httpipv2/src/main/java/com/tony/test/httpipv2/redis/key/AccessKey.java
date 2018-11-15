package com.tony.test.httpipv2.redis.key;

public class AccessKey extends BasePrefix {

    private AccessKey(int expireSeconds, String prefix, String key) {
        super(expireSeconds, prefix, key);
    }

    public static AccessKey withExpire(int expireSeconds, String key) {
        return new AccessKey(expireSeconds, "uri", key);
    }

}
