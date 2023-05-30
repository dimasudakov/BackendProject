package dev.dima.authservice.configs;

import dev.dima.authservice.utils.UUIDRedisSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.UUID;


@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String hostName;

    @Value("${spring.data.redis.port}")
    private int port;

    @Bean
    public RedisStandaloneConfiguration redisStandaloneConfiguration() {
        return new RedisStandaloneConfiguration(hostName, port);
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory(redisStandaloneConfiguration());
    }
    @Bean
    public RedisTemplate<UUID, String> redisTemplate() {
        RedisTemplate<UUID, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setKeySerializer(new UUIDRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }

}


