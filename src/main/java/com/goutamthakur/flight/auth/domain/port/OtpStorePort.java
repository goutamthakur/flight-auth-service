package com.goutamthakur.flight.auth.domain.port;

import com.goutamthakur.flight.auth.domain.enums.OtpPurpose;

public interface OtpStorePort {
    void saveOtp(OtpPurpose purpose, String identifier, String otp, long ttlSeconds);
    String getOtp(String key);
    void deleteOtp(String key);
}
