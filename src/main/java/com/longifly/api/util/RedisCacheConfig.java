package com.longifly.api.util;

import java.lang.reflect.Parameter;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

/**
 * @author WT
 * @date 2020/09/02
 */
@Configuration
public class RedisCacheConfig extends CachingConfigurerSupport {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        // TODO Auto-generated method stub
        return (target, method, params) -> {
            //
            boolean flag = true;
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName()).append(".");
            sb.append(method.getName()).append("[");
            Parameter[] parameters = method.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                //
                if (flag) {
                    //
                    flag = false;
                } else {
                    //
                    sb.append(", ");
                }
                //
                Parameter parameter = parameters[i];
                //
                sb.append(parameter.getName()).append("=").append(params[i]);
            }
            sb.append("]");
            return sb.toString();
        };
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        // TODO Auto-generated method stub
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(
            RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        redisCacheConfiguration = redisCacheConfiguration.disableCachingNullValues();
        redisCacheConfiguration = redisCacheConfiguration.entryTtl(Duration.ofSeconds(60));
        //
        return RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(redisCacheConfiguration).build();
    }

}
