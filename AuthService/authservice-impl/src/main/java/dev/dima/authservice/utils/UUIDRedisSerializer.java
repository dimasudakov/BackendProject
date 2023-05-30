package dev.dima.authservice.utils;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.util.UUID;

public class UUIDRedisSerializer implements RedisSerializer<UUID> {

    @Override
    public byte[] serialize(UUID uuid) throws SerializationException {
        if (uuid == null) {
            return null;
        }
        return uuid.toString().getBytes();
    }

    @Override
    public UUID deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null) {
            return null;
        }
        return UUID.fromString(new String(bytes));
    }
}