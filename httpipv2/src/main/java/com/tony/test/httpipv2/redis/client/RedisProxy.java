package com.tony.test.httpipv2.redis.client;

import com.tony.test.httpipv2.redis.key.KeyPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * 静态代理
 */
@Service
public class RedisProxy implements RedisKeyAPI {

    // 接收保存目标对象
    @Autowired
    private RedisAPI target;

    public RedisProxy() {
    }

    public RedisProxy(RedisAPI target) {
        this.target = target;
    }

    @Override
    public Object get(KeyPrefix key) {
        return target.get(key.getPrefix() + key.getKey());
    }

    @Override
    public void set(KeyPrefix key, Object value) {
        target.set(key.getPrefix() + key.getKey(), value);
    }

    @Override
    public void setex(KeyPrefix key, Object value) {
        target.setex(key.getPrefix() + key.getKey(), value, key.expireSeconds(), TimeUnit.SECONDS);
    }

    @Override
    public long incr(KeyPrefix key) {
        return target.incr(key.getPrefix() + key.getKey());
    }

    @Override
    public boolean zadd(KeyPrefix key, double score, Object value) {
        return target.zadd(key.getPrefix() + key.getKey(), score, value);
    }

    @Override
    public Collection zrange(KeyPrefix key, int start, int stop) {
        return target.zrange(key.getPrefix() + key.getKey(), start, stop);
    }

    @Override
    public Object hget(KeyPrefix key, String hkey) {
        return target.hget(key.getPrefix() + key.getKey(), hkey);
    }

    @Override
    public Object hset(KeyPrefix key, String hkey, String hval) {
        return target.hset(key.getPrefix() + key.getKey(), hkey, hval);
    }
}
