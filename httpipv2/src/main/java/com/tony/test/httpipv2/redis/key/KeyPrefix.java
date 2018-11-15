package com.tony.test.httpipv2.redis.key;

public interface KeyPrefix {

    /**
     * 过期时间
     *
     * @return
     */
    int expireSeconds();

    /**
     * 获取Key前缀
     *
     * @return
     */
    String getPrefix();

    /**
     * 获取Key
     *
     * @return
     */
    String getKey();

}
