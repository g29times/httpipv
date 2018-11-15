package com.tony.test.httpipv2.redis.client.redisson;

import com.tony.test.httpipv2.redis.client.AbstractRedisAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Priority;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * https://github.com/redisson/redisson/wiki/11.-Redis%E5%91%BD%E4%BB%A4%E5%92%8CRedisson%E5%AF%B9%E8%B1%A1%E5%8C%B9%E9%85%8D%E5%88%97%E8%A1%A8
 */
@Primary
@Priority(value = 0)
@Service
public class RedissonAPI extends AbstractRedisAPI {

    @Autowired
    RedissonDoc redissonDoc;

    public RedissonAPI() {
    }

    public RedissonAPI(RedissonDoc redissonDoc) {
        this.redissonDoc = redissonDoc;
    }

    @Override
    public boolean exists(String key) {
        return redissonDoc.getRBucket(key).isExists();
    }

    @Override
    public boolean del(String key) {
        return redissonDoc.getRBucket(key).delete();
    }

    @Override
    public boolean expire(String key, long expireTime, TimeUnit unit) {
        return redissonDoc.getRBucket(key).expire(expireTime, unit);
    }

    @Override
    public long ttl(String key) {
        return redissonDoc.getRBucket(key).remainTimeToLive();
    }

    @Override
    public Object scan(int cursor) {
        return redissonDoc.getRKeys().getKeys(cursor);
    }

    @Override
    public Object get(String key) {
        return redissonDoc.getRBucket(key).get();
    }

    @Override
    public void set(String key, Object value) {
        redissonDoc.getRBucket(key).set(value);
    }

    @Override
    public void setex(String key, Object value, long expireTime, TimeUnit unit) {
        redissonDoc.getRBucket(key).set(value, expireTime, unit);
    }

    @Override
    public boolean setnx(String key, Object value, long expireTime, TimeUnit unit) {
        return redissonDoc.getRBucket(key).trySet(value, expireTime, unit);
    }

    @Override
    public long incr(String key) {
        return redissonDoc.getRAtomicLong(key).incrementAndGet();
    }

    @Override
    public long decr(String key) {
        return redissonDoc.getRAtomicLong(key).decrementAndGet();
    }

    @Override
    public long strlen(String key) {
        return redissonDoc.getRBucket(key).size();
    }

    @Override
    public void setbit(String key, long offset, boolean value) {
        redissonDoc.getRBitMap(key).set(offset, value);
    }

    @Override
    public boolean getbit(String key, long offset) {
        return redissonDoc.getRBitMap(key).get(offset);
    }

    @Override
    public void bitop(String operation, String destkey, String... keys) {
        switch (operation) {
            case "and":
                redissonDoc.getRBitMap(destkey).and(keys);
            case "or":
                redissonDoc.getRBitMap(destkey).or(keys);
            case "xor":
                redissonDoc.getRBitMap(destkey).xor(keys);
            default:
                redissonDoc.getRBitMap(destkey).not();
        }
    }

    @Override
    public long bitcount(String key) {
        return redissonDoc.getRBitMap(key).cardinality();
    }

    @Override
    public Object hget(String key, String hkey) {
        return redissonDoc.getRMap(key).get(hkey);
    }

    @Override
    public Object hset(String key, String hkey, String hval) {
        return redissonDoc.getRMap(key).put(hkey, hval);
    }

    @Override
    public long hdel(String key, String... hkeys) {
        return redissonDoc.getRMap(key).fastRemove(hkeys);
    }

    @Override
    public int hlen(String key) {
        return redissonDoc.getRMap(key).size();
    }

    @Override
    public Collection<Object> hkeys(String key) {
        return redissonDoc.getRMap(key).readAllKeySet();
    }

    @Override
    public Collection<Object> hvals(String key) {
        return redissonDoc.getRMap(key).readAllValues();
    }

    @Override
    public Iterator hscan(String key, String type) {
        switch (type) {
            case "key":
                return redissonDoc.getRMap(key).keySet().iterator();
            case "value":
                return redissonDoc.getRMap(key).values().iterator();
            default:
                return redissonDoc.getRMap(key).entrySet().iterator();
        }
    }

    @Override
    public Map hmget(String key, Set hkeys) {
        return redissonDoc.getRMap(key).getAll(hkeys);
    }

    @Override
    public void hmset(String key, Map map) {
        redissonDoc.getRMap(key).putAll(map);
    }

    @Override
    public void lpush(String key, Object value) {
        redissonDoc.getRDeque(key).addFirst(value);
    }

    @Override
    public Object lpop(String key) {
        return redissonDoc.getRQueue(key).poll();
    }

    /**
     * 慎用 和标准API不一致
     *
     * @param key
     * @param start
     * @param stop
     * @return
     */
    @Override
    public List lrange(String key, int start, int stop) {
        return redissonDoc.getRList(key).readAll();
    }

    @Override
    public boolean lrem(String key, int count, Object value) {
        return redissonDoc.getRList(key).remove(value, count);
    }

    @Override
    public boolean rpush(String key, Object value) {
        return redissonDoc.getRList(key).add(value);
    }

    @Override
    public Object rpop(String key) {
        return redissonDoc.getRDeque(key).pollLast();
    }

    @Override
    public void lset(String key, int index, Object value) {
        redissonDoc.getRList(key).fastSet(index, value);
    }

    @Override
    public int linsert(String key, Object pivot, Object value, boolean isBefore) {
        if (isBefore)
            return redissonDoc.getRList(key).addBefore(pivot, value);
        else
            return redissonDoc.getRList(key).addAfter(pivot, value);
    }

    @Override
    public int llen(String key) {
        return redissonDoc.getRList(key).size();
    }

    @Override
    public Object lindex(String key, int index) {
        return redissonDoc.getRList(key).get(index);
    }

    @Override
    public void ltrim(String key, int from, int to) {
        redissonDoc.getRList(key).trim(from, to);
    }

    @Override
    public boolean sadd(String key, Object value) {
        return redissonDoc.getRSet(key).add(value);
    }

    @Override
    public boolean srem(String key, Object value) {
        return redissonDoc.getRSet(key).remove(value);
    }

    @Override
    public Iterator sscan(String key) {
        return redissonDoc.getRSet(key).iterator();
    }

    @Override
    public boolean zadd(String key, double score, Object value) {
        return redissonDoc.getRScoredSortedSet(key).add(score, value);
    }

    @Override
    public Collection zrange(String key, int start, int stop) {
        return redissonDoc.getRScoredSortedSet(key).valueRange(start, stop);
    }

    @Override
    public Iterator zscan(String key) {
        return redissonDoc.getRScoredSortedSet(key).iterator();
    }

    @Override
    public boolean pfadd(String key, Collection values) {
        return redissonDoc.getRHyperLogLog(key).addAll(values);
    }

    @Override
    public long pfcount(String key) {
        return redissonDoc.getRHyperLogLog(key).count();
    }

    @Override
    public void pfmerge(String newKey, String oldKeys) {
        redissonDoc.getRHyperLogLog(newKey).mergeWith(oldKeys);
    }

}
