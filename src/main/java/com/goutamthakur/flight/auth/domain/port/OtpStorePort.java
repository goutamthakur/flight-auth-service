package com.goutamthakur.flight.auth.domain.port;

public interface OtpStorePort {
    void saveOtp(String key, String otp, long ttlSeconds);
    String getOtp(String key);
    void deleteOtp(String key);
}
