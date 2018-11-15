package com.tony.test.httpipv2.redis.client;

import com.tony.test.httpipv2.redis.key.KeyPrefix;

import java.util.Collection;

public interface RedisKeyAPI {

    Object get(KeyPrefix key);

    void set(KeyPrefix key, Object value);

    void setex(KeyPrefix key, Object value);

    long incr(KeyPrefix key);

    boolean zadd(KeyPrefix key, double score, Object value);

    Collection zrange(KeyPrefix key, int start, int stop);

    Object hget(KeyPrefix key, String hkey);

    Object hset(KeyPrefix key, String hkey, String hval);
}
