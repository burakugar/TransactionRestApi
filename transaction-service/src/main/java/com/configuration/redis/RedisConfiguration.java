/*
package com.configuration.redis;

import com.constant.CacheNames;
import lombok.RequiredArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.ReadMode;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableCaching
@Configuration
@RequiredArgsConstructor
public class RedisConfiguration {

    private final RedisProperties redisProperties;
    private static final String REDIS_SCHEME = "redis://";

    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();
        template.setConnectionFactory(redissonConnectionFactory());
        return template;
    }

    @Bean
    public RedissonConnectionFactory redissonConnectionFactory() {
        return new RedissonConnectionFactory(constructRedissonClient());
    }

    private RedissonClient constructRedissonClient() {
        Config config = new Config();
        final ClusterServersConfig clusterServersConfig = config.useClusterServers();
        clusterServersConfig.setReadMode(ReadMode.SLAVE);
        constructNodeAddresses().forEach(clusterServersConfig::addNodeAddress);
        return Redisson.create(config);
    }

    private List<String> constructNodeAddresses() {
        List<String> nodeAddresses = new ArrayList<>();
        redisProperties.getNodes().forEach(node -> nodeAddresses.add(REDIS_SCHEME + node));
        return nodeAddresses;
    }

    @Bean
    public CacheManager cacheManager(RedissonConnectionFactory redissonConnectionFactory) {
        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
        RedisCacheConfiguration defaultCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues()
                .entryTtl(Duration.ofHours(4));
        cacheConfigurations.put(CacheNames.transactionName, defaultCacheConfiguration);
        return RedisCacheManager.builder(redissonConnectionFactory)
                .cacheDefaults(defaultCacheConfiguration)
                .withInitialCacheConfigurations(cacheConfigurations)
                .build();
    }
}

*/