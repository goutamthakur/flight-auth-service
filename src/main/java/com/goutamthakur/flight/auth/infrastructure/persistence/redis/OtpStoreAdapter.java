package com.goutamthakur.flight.auth.infrastructure.persistence.redis;

import com.goutamthakur.flight.auth.domain.port.OtpStorePort;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
public class OtpStoreAdapter implements OtpStorePort {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void saveOtp(String key, String otp, long ttlSeconds){
        redisTemplate.opsForValue().set(key, otp, ttlSeconds, TimeUnit.SECONDS);
    };

    @Override
    public String getOtp(String key){
        Object value = redisTemplate.opsForValue().get(key);
        return value != null ? value.toString() : null;
    };

    @Override
    public void deleteOtp(String key){
        redisTemplate.delete(key);
    };

}
