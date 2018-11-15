package com.tony.test.httpipv2.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RedisPoolFactory {

    @Autowired
    RedisConfig redisConfig;

    @Bean
    public RedissonClient getRedisson() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + redisConfig.getHost() + ":" + redisConfig.getPort());
//        config.useSingleServer()
//                .setAddress("redis://" + redisConfig.getHost() + ":" + redisConfig.getPort())
//                .setPassword(redisConfig.getPassword());
        // 添加主从配置
//        config.useMasterSlaveServers().setMasterAddress("").setPassword("").addSlaveAddress();
        return Redisson.create(config);
    }

}
