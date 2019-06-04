package com.wpp.redis.service.impl;

import com.wpp.redis.service.RedisClusterDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

@Service
public class RedisClusterDemoImpl implements RedisClusterDemo {
   /* @Autowired
    private JedisCluster jedisCluster;
    @Override
    public String findRedis() {
        jedisCluster.set("hello","world");
        return jedisCluster.get("hello");
    }*/
}
