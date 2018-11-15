package com.tony.test.httpipv2.redis;

import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Redisson的本地直联
 *
 * @author Tony Lee
 * @since 18.11.14
 */
@Component
public class RedissonDoc {

    @Autowired
    RedissonClient redissonClient;

    private String auth = "root";

    @Test
    public void redis() {
        /*****连接测试*****/
        try {
            if (redissonClient == null) {
                Config config = new Config();
                config.useSingleServer()
                        .setAddress("redis://127.0.0.1:6379");
//                        .setPassword(auth);
                redissonClient = Redisson.create(config);
            }
            redissonClient.getKeys();
        } catch (Exception e) {}
        // 连接本地的 Redis 服务
        System.out.println("Connection to server sucessfully");
        // 查看服务是否运行
        System.out.println("Server is running: " +
                ((Node) getNodesGroup().getNodes().iterator().next()).ping());

        // 字符串对象操作
        // 方式1 使用redisson原生操作
        getRBucket("hello").set("world");
        System.out.println(getRBucket("hello").get());

        // 方式2 使用包装类操作
        RedissonAPI redissonAPI = new RedissonAPI(this);
        RedisAPI redis = new RedisProxy(redissonAPI);
        redis.set("hello", "world-api");
        System.out.println(redis.get("hello"));
    }

    /**
     * 获取server实例
     *
     * @return
     */
    public NodesGroup getNodesGroup() {
        return redissonClient.getNodesGroup();
    }

    /**
     * 获取键
     *
     * @return
     */
    public RKeys getRKeys() {
        return redissonClient.getKeys();
    }

    /**
     * 获取字符串对象
     *
     * @param objectName
     * @return
     */
    public <T> RBucket<T> getRBucket(String objectName) {
        return redissonClient.getBucket(objectName);
    }

    /**
     * 获取位图
     *
     * @param objectName
     * @return
     */
    public RBitSet getRBitMap(String objectName) {
        return redissonClient.getBitSet(objectName);
    }

    /**
     * 获取Hash对象
     *
     * @param objectName
     * @return
     */
    public <K, V> RMap<K, V> getRMap(String objectName) {
        return redissonClient.getMap(objectName);
    }

    /**
     * 获取集合
     *
     * @param objectName
     * @return
     */
    public <V> RSet<V> getRSet(String objectName) {
        return redissonClient.getSet(objectName);
    }

    /**
     * 获取有序集合
     *
     * @param objectName
     * @return
     */
    public <V> RSortedSet<V> getRSortedSet(String objectName) {
        return redissonClient.getSortedSet(objectName);
    }

    /**
     * 获取分值有序集合
     *
     * @param objectName
     * @return
     */
    public <V> RScoredSortedSet<V> getRScoredSortedSet(String objectName) {
        return redissonClient.getScoredSortedSet(objectName);
    }

    /**
     * 获取列表
     *
     * @param objectName
     * @return
     */
    public <V> RList<V> getRList(String objectName) {
        return redissonClient.getList(objectName);
    }

    /**
     * 获取队列
     *
     * @param objectName
     * @return
     */
    public <V> RQueue<V> getRQueue(String objectName) {
        return redissonClient.getQueue(objectName);
    }

    /**
     * 获取双端队列
     *
     * @param objectName
     * @return
     */
    public <V> RDeque<V> getRDeque(String objectName) {
        return redissonClient.getDeque(objectName);
    }

    /**
     * 获取阻塞队列
     *
     * @param objectName
     * @param <V>
     * @return
     */
    public <V> RBlockingQueue<V> getRBlockingQueue(String objectName) {
        return redissonClient.getBlockingDeque(objectName);
    }

    /**
     * 获取锁
     *
     * @param objectName
     * @return
     */
    public RLock getRLock(String objectName) {
        return redissonClient.getLock(objectName);
    }

    /**
     * 获取读取锁
     *
     * @param objectName
     * @return
     */
    public RReadWriteLock getRWLock(String objectName) {
        return redissonClient.getReadWriteLock(objectName);
    }

    /**
     * 获取记数锁
     *
     * @param objectName
     * @return
     */
    public RCountDownLatch getRCountDownLatch(String objectName) {
        return redissonClient.getCountDownLatch(objectName);
    }

    /**
     * 获取原子数
     *
     * @param objectName
     * @return
     */
    public RAtomicLong getRAtomicLong(String objectName) {
        return redissonClient.getAtomicLong(objectName);
    }

    /**
     * 获取事务
     *
     * @param var1
     * @return
     */
    public RTransaction getRTransaction(TransactionOptions var1) {
        return redissonClient.createTransaction(var1);
    }

    /**
     * 获取消息的Topic
     *
     * @param objectName
     * @return
     */
    public RTopic getRTopic(String objectName) {
        return redissonClient.getTopic(objectName);
    }

    public RHyperLogLog getRHyperLogLog(String objectName) {
        return redissonClient.getHyperLogLog(objectName);
    }
}
