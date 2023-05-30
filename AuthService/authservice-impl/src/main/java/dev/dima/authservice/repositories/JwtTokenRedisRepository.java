package dev.dima.authservice.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JwtTokenRedisRepository {

    @Autowired
    private RedisTemplate<UUID, String> redisTemplate;

    public void saveRefreshToken(UUID userId, String refreshToken) {
        redisTemplate.opsForValue().set(userId, refreshToken, Duration.ofDays(7));
    }

    public String getRefreshToken(UUID userId) {
        return redisTemplate.opsForValue().get(userId);
    }
}
