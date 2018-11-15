package com.tony.test.httpipv2.redis.key;

public abstract class BasePrefix implements KeyPrefix {

    private int expireSeconds;

    private String prefix;

    private String key;

    public BasePrefix(String prefix, String key) { // 0代表永不过期
        this(0, prefix, key);
    }

    public BasePrefix(int expireSeconds, String prefix, String key) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
        this.key = key;
    }

    @Override
    public int expireSeconds() {//默认0代表永不过期
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className + ":" + prefix + "_";
    }

    @Override
    public String getKey() {
        return key;
    }
}

