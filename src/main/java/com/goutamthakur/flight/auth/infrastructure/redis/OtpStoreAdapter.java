package com.goutamthakur.flight.auth.infrastructure.redis;

import com.goutamthakur.flight.auth.domain.enums.OtpPurpose;
import com.goutamthakur.flight.auth.domain.port.OtpStorePort;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
public class OtpStoreAdapter implements OtpStorePort {

    private final RedisTemplate<String, Object> redisTemplate;
    private static final String PREFIX = "otp";

    @Override
    public void saveOtp(OtpPurpose purpose, String identifier, String otp, long ttlSeconds){
        String key = build(purpose, identifier);
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

    public static String build(OtpPurpose purpose, String identifier) {
        return PREFIX + ":" + purpose.name().toLowerCase() + ":" + identifier;
    }

}
