package com.tony.test.httpipv2.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 静态代理
 */
public class RedisProxy extends AbstractRedisAPI {

    private static Logger logger = LoggerFactory.getLogger(RedisProxy.class);

    // 接收保存目标对象
    private RedisAPI target;

    public RedisProxy(RedisAPI target) {
        this.target = target;
    }

    @Override
    public Object get(String key) {
        return target.get(key);
    }

    @Override
    public void set(String key, Object value) {
        logger.info("START");
        target.set(key, value);
        logger.info("END");
    }

}
