package com.arh.springbootdemo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author chenli
 * @Date 2020/7/13
 **/
@Configuration
@ConditionalOnProperty(name = "spring.redis.cluster.nodes", havingValue = "true", matchIfMissing = false)
public class RedisClusterConfig {
    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public RedisClusterConfiguration redisClusterConfiguration() {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        List<String> nodes = redisProperties.getCluster().getNodes();
        List<RedisNode> redisNodeList = new ArrayList<>();
        for (String node : nodes) {
            String[] parts = node.split(":");
            redisNodeList.add(new RedisNode(parts[0], Integer.valueOf(parts[1])));
        }
        redisClusterConfiguration.setPassword(redisProperties.getPassword());
        redisClusterConfiguration.setClusterNodes(redisNodeList);
        return redisClusterConfiguration;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisProperties.Pool pool = redisProperties.getJedis().getPool();
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(pool.getMaxActive());
        jedisPoolConfig.setMaxIdle(pool.getMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(pool.getMaxWait().toMillis());
        jedisPoolConfig.setMinIdle(pool.getMinIdle());
        jedisPoolConfig.setEvictorShutdownTimeoutMillis(redisProperties.getTimeout().toMillis());
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisClusterConfiguration(), jedisPoolConfig);
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate redisTemplate() {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }
}
