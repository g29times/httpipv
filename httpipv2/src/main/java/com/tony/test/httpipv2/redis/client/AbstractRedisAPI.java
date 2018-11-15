package com.tony.test.httpipv2.redis.client;

import java.util.*;
import java.util.concurrent.TimeUnit;

public abstract class AbstractRedisAPI implements RedisAPI {

    @Override
    public boolean exists(String key) {
        return false;
    }

    @Override
    public boolean del(String key) {
        return false;
    }

    @Override
    public boolean expire(String key, long expireTime, TimeUnit unit) {
        return false;
    }

    @Override
    public long ttl(String key) {
        return 0;
    }

    @Override
    public Object scan(int cursor) {
        return null;
    }

    @Override
    public Object get(String key) {
        return null;
    }

    @Override
    public void set(String key, Object value) {

    }

    @Override
    public void setex(String key, Object value, long expireTime, TimeUnit unit) {

    }

    @Override
    public boolean setnx(String key, Object value, long expireTime, TimeUnit unit) {
        return false;
    }

    @Override
    public long incr(String key) {
        return 0;
    }

    @Override
    public long decr(String key) {
        return 0;
    }

    @Override
    public long strlen(String key) {
        return 0;
    }

    @Override
    public void setbit(String key, long offset, boolean value) {

    }

    @Override
    public boolean getbit(String key, long offset) {
        return false;
    }

    @Override
    public void bitop(String operation, String destkey, String... keys) {

    }

    @Override
    public long bitcount(String key) {
        return 0;
    }

    @Override
    public Object hget(String key, String hkey) {
        return null;
    }

    @Override
    public Object hset(String key, String hkey, String hval) {
        return null;
    }

    @Override
    public long hdel(String key, String... hkeys) {
        return 0;
    }

    @Override
    public int hlen(String key) {
        return 0;
    }

    @Override
    public Collection<Object> hkeys(String key) {
        return null;
    }

    @Override
    public Collection<Object> hvals(String key) {
        return null;
    }

    @Override
    public Iterator hscan(String key, String type) {
        return null;
    }

    @Override
    public Map hmget(String key, Set hkeys) {
        return null;
    }

    @Override
    public void hmset(String key, Map map) {

    }

    @Override
    public void lpush(String key, Object value) {

    }

    @Override
    public Object lpop(String key) {
        return null;
    }

    @Override
    public List lrange(String key, int start, int stop) {
        return null;
    }

    @Override
    public boolean lrem(String key, int count, Object value) {
        return false;
    }

    @Override
    public boolean rpush(String key, Object value) {
        return false;
    }

    @Override
    public Object rpop(String key) {
        return null;
    }

    @Override
    public void lset(String key, int index, Object value) {

    }

    @Override
    public int linsert(String key, Object pivot, Object value, boolean isBefore) {
        return 0;
    }

    @Override
    public int llen(String key) {
        return 0;
    }

    @Override
    public Object lindex(String key, int index) {
        return null;
    }

    @Override
    public void ltrim(String key, int from, int to) {

    }

    @Override
    public boolean sadd(String key, Object value) {
        return false;
    }

    @Override
    public boolean srem(String key, Object value) {
        return false;
    }

    @Override
    public Iterator sscan(String key) {
        return null;
    }

    @Override
    public boolean zadd(String key, double score, Object value) {
        return false;
    }

    @Override
    public Collection zrange(String key, int start, int stop) {
        return null;
    }

    @Override
    public Iterator zscan(String key) {
        return null;
    }

    @Override
    public boolean pfadd(String key, Collection values) {
        return false;
    }

    @Override
    public long pfcount(String key) {
        return 0;
    }

    @Override
    public void pfmerge(String newKey, String oldKeys) {

    }
}
