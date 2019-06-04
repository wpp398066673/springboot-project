package com.wpp.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.EnumSetSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisClusterConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    /*@Value("${spring.redis.cluster.nodes}")
    private String clusterNodes;

    @Bean
    public JedisCluster getJedisCluster(){
        //分割集群节点
        String[] cNodes = clusterNodes.split(",");
        //创建set集合对象
        Set<HostAndPort> nodes = new HashSet<>();
        for(String node : cNodes){
            String[] hp = node.split(":");
            nodes.add(new HostAndPort(hp[0],Integer.parseInt(hp[1])));
        }
        //创建集群对象
        JedisCluster jedisCluster = new JedisCluster(nodes);
        return jedisCluster;
    }*/

    //自定义缓存key生成器
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                StringBuffer sb = new StringBuffer();
                sb.append(o.getClass().getName());
                sb.append(method.getName());
                for (Object obj : objects) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

    @Bean(name="redisTemplate")
    @ConditionalOnMissingBean(name="redisTemplate")
    public RedisTemplate<String,String> redisTemplate(RedisConnectionFactory factory){
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(factory);
        setSerializer(stringRedisTemplate);
        stringRedisTemplate.afterPropertiesSet();
        return stringRedisTemplate;
    }

    public void setSerializer(StringRedisTemplate stringRedisTemplate){
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        stringRedisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
    }


}
